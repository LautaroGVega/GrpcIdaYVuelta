package user;

import com.yrrhelp.grpc.User.APIResponse;
import com.yrrhelp.grpc.User.Empty;
import com.yrrhelp.grpc.User.LoginRequest;
import com.yrrhelp.grpc.userGrpc.userImplBase;

import io.grpc.stub.StreamObserver;

public class UserService extends userImplBase{

		@Override
	public void login(LoginRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("Inicio de sesión");
	
		String username = request.getUsername();
		String password = request.getPassword();
	
		APIResponse.Builder response = APIResponse.newBuilder();
		if (username.equals(password)) {
			// return success message
			response.setResponseCode(0).setResponsemessage("Inicio aprobado para el usuario: " + username);
			System.out.println("Usuario '" + username + "' ha iniciado sesión."); // Esta línea imprime el nombre del usuario
		} else {
			response.setResponseCode(100).setResponsemessage("Contraseña no válida");
		}
	
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}


	@Override
	public void logout(Empty request, StreamObserver<APIResponse> responseObserver) {
		
	}

	
	 
}
