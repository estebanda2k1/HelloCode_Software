package Gamificacion_Modulo.GUI.Ranking;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.*;
import java.util.stream.Collectors;

// Student data class - separate from main class
class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() { return name; }
    public int getScore() { return score; }
}

public class Main extends Application {

    // Sample student data - replace with your actual data
    private List<Student> allStudents = Arrays.asList(
            new Student("Julian Narvaez", 100),
            new Student("Evelin Rocha", 95),
            new Student("Esteban Rocha", 94),
            new Student("Abdelrahman Tarek", 93),
            new Student("Maria Garcia", 92),
            new Student("Carlos Lopez", 91),
            new Student("Ana Martinez", 90),
            new Student("Pedro Sanchez", 89),
            new Student("Sofia Rodriguez", 88),
            new Student("Diego Fernandez", 87),
            new Student("Lucia Gonzalez", 86),
            new Student("Miguel Torres", 85),
            new Student("Isabella Morales", 84),
            new Student("Antonio Ruiz", 83),
            new Student("Valentina Castro", 82)
    );

    private String currentUserName = "Julian Narvaez"; // The user whose ranking we're showing

    @Override
    public void start(Stage primaryStage) {
        // Main container
        VBox rankingContainer = new VBox();
        rankingContainer.setPrefSize(393, 852);
        rankingContainer.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1;");

        // Header section with purple background and profile picture
        StackPane headerSection = createHeaderSection();

        // Get top 10 students and current user's position
        List<Student> topStudents = getTopStudents(7);
        int currentUserPosition = getCurrentUserPosition();

        // Title text with dynamic position
        Label titleLabel = new Label(currentUserName + " ocupas el puesto #" + currentUserPosition);
        titleLabel.setFont(Font.font("Inter", FontWeight.BOLD, 25));
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(20, 0, 20, 0));

        // Ranking list section with top students
        VBox rankingSection = createRankingSection(topStudents);

        // Navigation bar
        HBox navigationBar = createNavigationBar();

        // Add spacer to push navigation to bottom
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Assemble the layout
        rankingContainer.getChildren().addAll(headerSection, titleLabel, rankingSection, spacer, navigationBar);

        // Create scene and stage
        Scene scene = new Scene(rankingContainer);
        primaryStage.setTitle("User Ranking");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private List<Student> getTopStudents(int count) {
        return allStudents.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore())) // Sort by score descending
                .limit(count)
                .collect(Collectors.toList());
    }

    private int getCurrentUserPosition() {
        List<Student> sortedStudents = allStudents.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
                .collect(Collectors.toList());

        for (int i = 0; i < sortedStudents.size(); i++) {
            if (sortedStudents.get(i).getName().equals(currentUserName)) {
                return i + 1; // Position is 1-indexed
            }
        }
        return -1; // User not found
    }

    private StackPane createHeaderSection() {
        StackPane headerSection = new StackPane();
        headerSection.setPrefHeight(272);

        // Purple rectangle background
        Region purpleBackground = new Region();
        purpleBackground.setPrefHeight(195);
        purpleBackground.setStyle("-fx-background-color: #424874;");
        StackPane.setAlignment(purpleBackground, Pos.TOP_CENTER);

        Image image = new Image(getClass().getResource("Ellipse 4.png").toExternalForm());
        ImageView profilePicture = new ImageView(image);

        // Optional: set dimensions if you want to resize
        profilePicture.setFitWidth(180);
        profilePicture.setFitHeight(174);
        profilePicture.setPreserveRatio(true);

        // Set alignment and margin just like before
        StackPane.setAlignment(profilePicture, Pos.CENTER);
        StackPane.setMargin(profilePicture, new Insets(50, 0, 0, 0)); // offset down from center
        headerSection.getChildren().addAll(purpleBackground, profilePicture);
        return headerSection;
    }

    private VBox createRankingSection(List<Student> topStudents) {
        VBox rankingContainer = new VBox();
        rankingContainer.setMaxWidth(337);
        rankingContainer.setAlignment(Pos.CENTER);
        rankingContainer.setPadding(new Insets(20));
        rankingContainer.setStyle("-fx-background-color: white; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 1; " +
                "-fx-background-radius: 10; " +
                "-fx-border-radius: 10;");

        // Add drop shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.1));
        dropShadow.setRadius(10);
        dropShadow.setSpread(0);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(0);
        rankingContainer.setEffect(dropShadow);

        // Center the ranking container
        VBox centerWrapper = new VBox();
        centerWrapper.setAlignment(Pos.CENTER);
        centerWrapper.setPadding(new Insets(20));

        // Create ranking entries from top students
        for (int i = 0; i < topStudents.size(); i++) {
            Student student = topStudents.get(i);
            HBox rankingItem = new HBox();
            rankingItem.setAlignment(Pos.CENTER_LEFT);
            rankingItem.setPrefWidth(295);

            Label nameLabel = new Label(student.getName());
            nameLabel.setFont(Font.font("Inter", FontWeight.BOLD, 18));

            // Highlight current user if they're in top 10
            if (student.getName().equals(currentUserName)) {
                nameLabel.setTextFill(Color.BLUE);
            } else {
                nameLabel.setTextFill(Color.BLACK);
            }

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            Label expLabel = new Label(student.getScore() + " EXP");
            expLabel.setFont(Font.font("Inter", FontWeight.BOLD, 18));

            if (student.getName().equals(currentUserName)) {
                expLabel.setTextFill(Color.BLUE);
            } else {
                expLabel.setTextFill(Color.BLACK);
            }

            rankingItem.getChildren().addAll(nameLabel, spacer, expLabel);
            rankingContainer.getChildren().add(rankingItem);

            if (i < topStudents.size() - 1) {
                // Add spacing between items
                Region itemSpacer = new Region();
                itemSpacer.setPrefHeight(20);
                rankingContainer.getChildren().add(itemSpacer);
            }
        }

        centerWrapper.getChildren().add(rankingContainer);
        return centerWrapper;
    }

    private HBox createNavigationBar() {
        HBox navigationBar = new HBox();
        navigationBar.setPrefHeight(74);
        navigationBar.setAlignment(Pos.CENTER);
        navigationBar.setSpacing(30); // Space between buttons
        navigationBar.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1 0 0 0;");


        // Image file names
        String[] imageFiles = {
                "Rectangle 44.png",
                "Rectangle 45.png",
                "image 13.png"
        };

        for (int i = 0; i < imageFiles.length; i++) {
            String imageFile = imageFiles[i];
            // Load image
            Image img = new Image(getClass().getResource(imageFile).toExternalForm());
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            imageView.setPreserveRatio(true);

            // Create button with image
            Button navButton = new Button();
            navButton.setPrefSize(65, 60);
            navButton.setGraphic(imageView);
            navButton.setStyle("-fx-background-color: #a6b1e1; " +
                    "-fx-background-radius: 5; " +
                    "-fx-border-radius: 5;");

            // Hover effect
            navButton.setOnMouseEntered(e -> navButton.setStyle("-fx-background-color: #9fa8da; -fx-background-radius: 5; -fx-border-radius: 5;"));
            navButton.setOnMouseExited(e -> navButton.setStyle("-fx-background-color: #a6b1e1; -fx-background-radius: 5; -fx-border-radius: 5;"));

            int buttonNumber = i + 1; // To capture correct index in lambda
            navButton.setOnAction(e -> {
                Stage newWindow = new Stage();
                newWindow.setTitle("Button " + buttonNumber);
                Label label = new Label("You clicked Button " + buttonNumber);
                label.setStyle("-fx-font-size: 20px; -fx-padding: 20px;");
                Scene scene = new Scene(new StackPane(label), 300, 150);
                newWindow.setScene(scene);
                newWindow.show();
            });

            // Add to your container (e.g., HBox or VBox)
            navigationBar.getChildren().add(navButton);
        }


        return navigationBar;
    }

    public static void main(String[] args) {
        launch(args);
    }
}