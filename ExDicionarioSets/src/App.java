import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// textos retirados de http://sitenotadez.net/digitacao-concursos/

public class App {

	static HashMap dic = new HashMap();
	static String[] palavras;

	public static void main(String[] args) {

		String[] files = new String[] { "texto01.txt", "texto02.txt", "texto03.txt", "texto04.txt", "texto05.txt",
				"texto06.txt", "texto07.txt", "texto08.txt", "texto09.txt", "texto10.txt" };
		ArrayList<String> b = new ArrayList<>();
		String a = "arquimedes";
		String c = "nasceu";
		b.add(a);
		b.add(c);
		for (String fname : files) {

			// for (String fname : args) {
			// System.out.format("%nFile: %s%n", fname);

			try {
				maisPalavras(b, fname);
				// ArrayList<String> c = procura(a, fname);
				// if (!c.isEmpty()) {
				// System.out.println(c);
				// }
				// carregaDados(fname);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void maisPalavras(ArrayList<String> list, String fileName) throws IOException {
		ArrayList<String> file = new ArrayList<>();
		ArrayList<String> copy = new ArrayList<>();
		int count = 0;
		for (String pala : list) {
			file.addAll(procura(pala, fileName));
			copy = file;
		}
		for (int i = 0; i < file.size(); i++) {
			count = 0;
			for (int j = 0; j < copy.size(); j++) {
				if (i != j && file.get(i).equals(copy.get(j))) {
					count++;
				}
			}
			if (count == 1) {
				System.out.println(file.get(i));
			}
		}
	}

	public static ArrayList<String> procura(String a, String fileName) throws IOException {
		ArrayList<String> arquivos = new ArrayList<>();
		ArrayList<String> arq = new ArrayList<>();
		Path path1 = Paths.get(fileName);
		try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(path1, Charset.forName("utf8")))) {
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.toLowerCase().replaceAll("[^a-zA-Záéíóúçãõà-]", " ");
				palavras = line.split(" ");
				for (int i = 0; i < palavras.length; i++) {
					dic.put(i, palavras[i]);
					// System.out.println(dic.get(i));
					if (palavras[i].equals(a)) {
						if (!arquivos.contains(fileName)) {
							arquivos.add(fileName);
						}
					}
				}
			}
		}
		return arquivos;
	}

	public static void carregaDados(String fileName) throws IOException {
		Path path1 = Paths.get(fileName);
		try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(path1, Charset.forName("utf8")))) {
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.toLowerCase().replaceAll("[^a-zA-Záéíóúçãõà-]", " ");
				String[] palavras = line.split(" ");
				for (String pal : palavras) {
					if (pal != null && !pal.equals(""))
						System.out.format("<%s>", pal);
				}
				System.out.format("\n");

			}
		}
	}

}
