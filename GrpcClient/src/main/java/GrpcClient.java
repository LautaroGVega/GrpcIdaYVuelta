import com.yrrhelp.grpc.User.APIResponse;
import com.yrrhelp.grpc.User.LoginRequest;
import com.yrrhelp.grpc.userGrpc;
import com.yrrhelp.grpc.userGrpc.userBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("192.168.56.1", 9090).usePlaintext().build();
        userBlockingStub userStub = userGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        LoginRequest loginRequest = LoginRequest.newBuilder()
            .setUsername(username)
            .setPassword(password)
            .build();

        APIResponse response = userStub.login(loginRequest);

        System.out.println(response.getResponsemessage());

        // Cerrar el canal y el scanner después de su uso
        channel.shutdown();
        scanner.close();
    }
}
