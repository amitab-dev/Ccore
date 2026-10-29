package amitab.drive;

import org.springframework.boot.SpringApplication;

public class TestDriveApplication {

    public static void main(String[] args) {
        SpringApplication.from(DriveApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
