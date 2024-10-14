import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PongGame extends Application {

    // Ukuran layar game
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    // Paddle ukuran dan posisi
    private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 100;
    private double paddleY = HEIGHT / 2 - PADDLE_HEIGHT / 2;

    // Ukuran dan posisi bola
    private double ballX = WIDTH / 2;
    private double ballY = HEIGHT / 2;
    private double ballSpeedX = 5;
    private double ballSpeedY = 3;
    private static final int BALL_SIZE = 20;

    // Kecepatan paddle
    private double paddleSpeed = 10;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Timeline untuk mengatur gerakan bola dan paddle
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        Scene scene = new Scene(new javafx.scene.Group(canvas));
        
        // Event listener untuk gerakan paddle
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP && paddleY > 0) {
                paddleY -= paddleSpeed;
            } else if (e.getCode() == KeyCode.DOWN && paddleY < HEIGHT - PADDLE_HEIGHT) {
                paddleY += paddleSpeed;
            }
        });

        stage.setScene(scene);
        stage.setTitle("Pong Game Sederhana");
        stage.show();
        
        timeline.play();
    }

    // Logika permainan
    private void run(GraphicsContext gc) {
        // Menggerakkan bola
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Memantulkan bola dari dinding atas dan bawah
        if (ballY <= 0 || ballY >= HEIGHT - BALL_SIZE) {
            ballSpeedY *= -1;
        }

        // Jika bola menyentuh paddle, pantulkan kembali
        if (ballX <= PADDLE_WIDTH && ballY >= paddleY && ballY <= paddleY + PADDLE_HEIGHT) {
            ballSpeedX *= -1;
        }

        // Jika bola keluar dari layar, reset posisi
        if (ballX <= 0 || ballX >= WIDTH) {
            ballX = WIDTH / 2;
            ballY = HEIGHT / 2;
            ballSpeedX *= -1;
        }

        // Gambar ulang latar belakang, bola, dan paddle
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
        gc.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
