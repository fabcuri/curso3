package br.com.treinamento.curso3.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.JOptionPane;

public class Log {
	private final static String FILE_PATH = "C:\\Users\\SAMSUNG\\Desktop\\Log\\Demonstrativo_Log.txt";
	// private final static String FILE_PATH =
	// "C:\\Users\\fabricio.curi\\Desktop\\produtos_log.csv";
	private LocalDateTime log;
	private String funcaoUtilizada;

	@Override
	public String toString() {
		return "log=" + log + " " + funcaoUtilizada;
	}

	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Log(LocalDateTime log, String funcaoUtilizada) {
		super();
		this.log = log;
		this.funcaoUtilizada = funcaoUtilizada;
	}

	public LocalDateTime getLog() {
		return log;
	}

	public void setLog(LocalDateTime log) {
		this.log = log;
	}

	public String getFuncaoUtilizada() {
		return funcaoUtilizada;
	}

	public void setFuncaoUtilizada(String funcaoUtilizada) {
		this.funcaoUtilizada = funcaoUtilizada;
	}

	public static void escreverLog1() throws IOException {

		Log documento = new Log();
		documento.setLog(LocalDateTime.now());
		documento.setFuncaoUtilizada("Cadastrar Curso");
		insereController1(documento);

	}

	public static void insereController1(Log documento) {
		insereModel1(documento);
	}

	public static void insereModel1(Log documento) {
		File f = new File(FILE_PATH);
		try {
			FileOutputStream fos = new FileOutputStream(f, true);
			fos.write(new String(documento.toString() + "\n").getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void escreverLog2() throws IOException {

		Log documento = new Log();
		documento.setLog(LocalDateTime.now());
		documento.setFuncaoUtilizada("Consultar Produtos");
		insereController2(documento);

	}

	public static void insereController2(Log documento) {
		insereModel2(documento);
	}

	public static void insereModel2(Log documento) {
		File f = new File(FILE_PATH);
		try {
			FileOutputStream fos = new FileOutputStream(f, true);
			fos.write(new String(documento.toString() + "\n").getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void escreverLog3() throws IOException {

		Log documento = new Log();
		documento.setLog(LocalDateTime.now());
		documento.setFuncaoUtilizada("Deletar Produto");
		insereController3(documento);

	}

	public static void insereController3(Log documento) {
		insereModel3(documento);
	}

	public static void insereModel3(Log documento) {
		File f = new File(FILE_PATH);
		try {
			FileOutputStream fos = new FileOutputStream(f, true);
			fos.write(new String(documento.toString() + "\n").getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void escreverLog4() throws IOException {

		Log documento = new Log();
		documento.setLog(LocalDateTime.now());
		documento.setFuncaoUtilizada("Excluir Produto");
		insereController4(documento);

	}

	public static void insereController4(Log documento) {
		insereModel4(documento);
	}

	public static void insereModel4(Log documento) {
		File f = new File(FILE_PATH);
		try {
			FileOutputStream fos = new FileOutputStream(f, true);
			fos.write(new String(documento.toString() + "\n").getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void lerViewLog() {
		String conteudo = lerModelLog();
		int linhas = conteudo.split("\n").length;
		JOptionPane.showMessageDialog(null, "Total de log:" + linhas + "\n" + conteudo);
	}

	public static String lerModelLog() {
		String retorno = "";
		File f = new File(FILE_PATH);
		try {
			FileInputStream fis = new FileInputStream(f);
			byte[] conteudo = fis.readAllBytes();
			retorno = new String(conteudo);
			retorno = retorno.replaceAll(";", " - ");
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
