import java.io.RandomAccessFile;
import java.util.Scanner;


public class Main {
	
	public static void main(String args[]) throws Exception
	{
		String linha;
		String colunas[];
        BTree<String, String> arvb = new BTree<String, String>();
		RandomAccessFile f1 = new RandomAccessFile("bolsa.csv", "r");

		f1.readLine();
		while(f1.getFilePointer() < f1.length())
		{
			linha = f1.readLine();
			colunas = linha.split("\t");
            arvb.put(colunas[7], linha);
		}
				
		System.out.print("Digite um NIS a ser buscado: ");
		Scanner sc = new Scanner(System.in);
		String nisDigit = sc.nextLine();
		sc.close();
		
		double tempoInicio = System.nanoTime();
		
		boolean encontra = false;
		
		String nisProcurado = arvb.get(nisDigit);
		
		if(nisProcurado != null) {
			encontra = true;
		} else {
			encontra = false;
		}
		
		if (encontra) {
			double tempoParada = System.nanoTime();
			double tempoFinal = tempoParada - tempoInicio;
			
		    System.out.println("\nNIS encontrado!");
		    System.out.println("Tempo gasto para encontr�-lo: " + tempoFinal + " nanossegundos");
			System.out.println("Tempo m�dio gasto para encontrar uma chave na �rvore: " + calculaMedia(f1, arvb) + " nanossegundos");
			
			colunas = nisProcurado.split("\t");
			
			System.out.println("\nAltura da �rvore: " + arvb.height());
			System.out.println("Tamanho da �rvore: " + arvb.size());
			
			System.out.println("\nUF: " + colunas[0]);
			System.out.println("C�digo SIAFI Munic�pio: " + colunas[1]);
			System.out.println("Nome Munic�pio: " + colunas[2]);
			System.out.println("C�digo Fun��o: " + colunas[3]);
			System.out.println("C�digo Subfun��o: " + colunas[4]);
			System.out.println("C�digo Programa: " + colunas[5]);
			System.out.println("C�digo A��o: " + colunas[6]);
			System.out.println("NIS Favorecido: " + colunas[7]);
			System.out.println("Nome Favorecido: " + colunas[8]);
			System.out.println("Fonte-Finalidade: " + colunas[9]);
			System.out.println("Valor Parcela: " + colunas[10]);
			System.out.println("M�s Compet�ncia: " + colunas[11]);
		} else {
			System.out.println("NIS n�o encontrado.");
		}
		
        f1.close();
	}
	
	public static double calculaMedia(RandomAccessFile f1, BTree arvoreb) throws Exception {
		double media = 0;
		double soma = 0;
		int qtd = 0;
		String linhaMedia;
		String colunasMedia[];
		
		f1.seek(0);
		f1.readLine();
		
		while(f1.getFilePointer() < f1.length())
		{
			double tempoInicioMedia = System.nanoTime();
			linhaMedia = f1.readLine();
			colunasMedia = linhaMedia.split("\t");
			arvoreb.get(colunasMedia[7]);
			double tempoParadaMedia = System.nanoTime();
			double tempoFinalMedia = tempoParadaMedia - tempoInicioMedia;
			soma += tempoFinalMedia;
			qtd++;
		}
		
		media = soma/qtd;
		
		return media;
	}
}