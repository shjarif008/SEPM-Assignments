import javafx.application.Application;
import javafx.application.Platform;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Q {
    String txt, a, b, c, d, correct;
    Q(String q, String o1, String o2, String o3, String o4, String ans) {
        txt = q; a = o1; b = o2; c = o3; d = o4; correct = ans;
    }
}

public class QuizGame extends Application {
    String url = "jdbc:mysql://localhost:3306/quiz_db";
    String user = "root";
    String pass = "password";

    List<Q> list = new ArrayList<>();
    int idx = 0, pts = 0, sec = 10;
    
    Label qLab, tLab, sLab;
    RadioButton r1, r2, r3, r4;
    ToggleGroup grp;
    Timeline time;

    @Override
    public void start(Stage stage) {
        qLab = new Label();
        tLab = new Label();
        sLab = new Label("Score: 0");
        grp = new ToggleGroup();
        r1 = new RadioButton(); r1.setToggleGroup(grp);
        r2 = new RadioButton(); r2.setToggleGroup(grp);
        r3 = new RadioButton(); r3.setToggleGroup(grp);
        r4 = new RadioButton(); r4.setToggleGroup(grp);

        Button nBtn = new Button("Next");
        nBtn.setOnAction(e -> next());

        Button eBtn = new Button("Exit");
        eBtn.setOnAction(e -> Platform.exit());

        VBox root = new VBox(10, tLab, sLab, qLab, r1, r2, r3, r4, nBtn, eBtn);
        root.setAlignment(Pos.CENTER);

        load();
        show();
        clock();

        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    void load() {
        try (Connection c = DriverManager.getConnection(url, user, pass)) {
            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM qs ORDER BY RAND() LIMIT 5");
            while (rs.next()) {
                list.add(new Q(rs.getString("q"), rs.getString("o1"), rs.getString("o2"), 
                               rs.getString("o3"), rs.getString("o4"), rs.getString("ans")));
            }
        } catch (Exception e) {}
    }

    void show() {
        if (idx < list.size()) {
            Q cur = list.get(idx);
            qLab.setText(cur.txt);
            r1.setText(cur.a); r2.setText(cur.b); r3.setText(cur.c); r4.setText(cur.d);
            grp.selectToggle(null);
            sec = 10;
        } else {
            done();
        }
    }

    void clock() {
        time = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            sec--;
            tLab.setText("Time: " + sec);
            if (sec <= 0) next();
        }));
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
    }

    void next() {
        RadioButton sel = (RadioButton) grp.getSelectedToggle();
        if (sel != null && sel.getText().equals(list.get(idx).correct)) {
            pts++;
            sLab.setText("Score: " + pts);
        }
        idx++;
        show();
    }

    void done() {
        time.stop();
        TextInputDialog d = new TextInputDialog("Player");
        d.setHeaderText("Final Score: " + pts);
        d.setContentText("Name:");
        d.showAndWait().ifPresent(name -> save(name, pts));

        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Restart?", ButtonType.YES, ButtonType.NO);
        a.showAndWait().ifPresent(r -> {
            if (r == ButtonType.YES) reset();
            else Platform.exit();
        });
    }

    void save(String n, int s) {
        try (Connection c = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO results (name, score) VALUES (?, ?)");
            ps.setString(1, n);
            ps.setInt(2, s);
            ps.executeUpdate();
        } catch (Exception e) {}
    }

    void reset() {
        idx = 0; pts = 0;
        sLab.setText("Score: 0");
        list.clear();
        load();
        show();
        time.play();
    }

    public static void main(String[] args) { launch(args); }
}
