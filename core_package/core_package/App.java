package core_package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;


public class App {

    public static void main(String[] args) {
        int Opcao = 0; // Variável para armazenar a opção escolhida pelo utilizador

        // Criar um scanner para entrada do utilizador
        Scanner scanner = new Scanner(System.in);

        // Loop do menu principal
        do {
            // Exibir menu de opções
            System.out.println("\nMenu:");
            System.out.println("1 - Gravar Ficheiro");
            System.out.println("2 - Ler Ficheiro");
            System.out.println("3 - Encriptar Ficheiro");
            System.out.println("4 - Desencriptar Ficheiro");
            System.out.println("0 - Terminar");
            System.out.print("\nEscolha (0-4): ");

            // Validação da entrada do utilizador
            if (scanner.hasNextInt()) {
                Opcao = scanner.nextInt(); // Ler a opção

                // Estrutura switch para executar a opção escolhida
                switch (Opcao) {
                    case 1:
                        GravarFicheiro(); 
                        break;
                    case 2:
                        LerFicheiro(); 
                        break;
                    case 3:
                        EncriptarFicheiro(); 
                        break;
                    case 4:
                        DesencriptarFicheiro(); 
                        break;
                    case 0:
                        System.out.println("\nA Encerrar Programa...");
                        scanner.close(); // Fecha o scanner
                        break;
                    default:
                        System.out.println("\nOpção inválida. Por favor, escolha um número entre 0 e 4.");
                        break;
                }
            } else {
                // Mensagem para entrada inválida
                System.out.println("\nEntrada inválida. Por favor, insira um número entre 0 e 4.");
                scanner.next(); // Limpar entrada inválida
            }
        } while (Opcao != 0); // Repetir até que a opção seja 0
    }

    //===========================================================

    // Função para gravar texto em um ficheiro
    public static void GravarFicheiro() {
        Scanner sc = new Scanner(System.in);

        // Solicitar nome e caminho do ficheiro
        System.out.print("Introduza o nome, com o caminho completo, do ficheiro: ");
        String nomeFicheiro = sc.nextLine();

        // Bloco try-with-resources para escrita no ficheiro
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro))) {
            System.out.println("Introduza o texto a gravar no ficheiro (ENTER para terminar):");
            
            String conteudo;
            // Ler entrada do utilizador até encontrar uma linha vazia
            while (!(conteudo = sc.nextLine()).isEmpty()) {
                writer.write(conteudo); // Escrever no ficheiro
                writer.newLine(); // Adicionar nova linha
            }
            System.out.println("Texto gravado com sucesso no ficheiro!");
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao gravar no ficheiro: " + e.getMessage());
        }
    }

    // Função para ler um ficheiro
    private static void LerFicheiro(){
        Scanner sc = new Scanner(System.in);
    
        // Solicitar nome e caminho do ficheiro
        System.out.print("Introduza o nome, com o caminho completo, do ficheiro: ");
        String nomeFicheiro = sc.nextLine();
        
        // Utilização do 'try' para leitura do ficheiro
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String conteudo;
            System.out.println("\nConteúdo do ficheiro:\n");
            while ((conteudo = reader.readLine()) != null) {
                System.out.println(conteudo); // Exibir conteúdo do ficheiro
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }
        
   // Função para encriptar texto em um ficheiro
    private static void EncriptarFicheiro() {
        Scanner sc = new Scanner(System.in);

        // Solicitar nome do ficheiro
        System.out.print("Introduza o nome, com o caminho completo, do ficheiro: ");
        String nomeFicheiro = sc.nextLine();

        int k = 3; // Chave de encriptação
        StringBuilder strFinal = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String conteudo;

            while ((conteudo = reader.readLine()) != null) {
                if (!conteudo.isEmpty()) {
                    conteudo = normalizeString(conteudo); // Normalizar texto

                    // Método para encriptar cada caractere
                    for (char c : conteudo.toCharArray()) {
                        if (c >= 'a' && c <= 'z') {
                            int dc = c - 'a' + k;
                            dc = dc % 26;
                            strFinal.append((char) (dc + 'a'));
                            k = dc;
                        } else {
                            strFinal.append(c);
                        }
                    }
                }
            }
            System.out.println("Texto encriptado: \n" + strFinal.toString());

            // Método para gravar texto encriptado
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro))) {
                writer.write(strFinal.toString());
                System.out.println("Texto encriptado gravado com sucesso no ficheiro!");
            } catch (Exception e) {
                System.out.println("Erro ao gravar o ficheiro: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        }

    }

    // Função para normalizar texto
    private static String normalizeString(String input) {    
        return input.toLowerCase(); // Conversão da string para minúsculas
    }

    // Função para desencriptar texto
    private static void DesencriptarFicheiro(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduza o nome, com o caminho completo, do ficheiro: ");
        String nomeFicheiro = sc.nextLine();

        int k = 3; // Chave de desencriptação
        StringBuilder strFinal = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFicheiro))) {
            String conteudo;

            while ((conteudo = reader.readLine()) != null) {
                for (char c : conteudo.toCharArray()) {
                    if (c >= 'a' && c <= 'z') {
                        int dc = c - 'a' - k;
                        if (dc < 0) dc += 26;
                        strFinal.append((char) (dc + 'a'));
                        k = c - 'a';
                    } else {
                        strFinal.append(c);
                    }
                }
            }
            System.out.println("Texto desencriptado: \n" + strFinal.toString());
        } catch (Exception e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }
}
