package Controlador;

import java.io.IOException;

public class MainPhyton {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ProcessBuilder pb = new ProcessBuilder(
				"python","C:\\Users\\alumno\\Desktop\\Psp2\\Unidad1_Multiprocesos\\src\\main\\resources\\phyton.py"
				);
		pb.inheritIO();
		pb.start();
			
	}

}
