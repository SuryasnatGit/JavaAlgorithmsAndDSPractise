package com.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InMemoryFileSystem {

	public class File {
		String fileName = "";
		String extension = "";
		long size = 0;
		Calendar createdTime = Calendar.getInstance();
		Calendar modifiedTime = Calendar.getInstance();
		Calendar lastAccessTime = Calendar.getInstance();
		boolean readOnly = false;
		boolean hidden = false;
		boolean isExecutable = false;
		boolean isDirctory = false;
		File upperLevel = null;
		Set<File> children = new HashSet<File>();

		public String getName() {
			if (isDirctory)
				return fileName;
			else
				return fileName + "." + extension;
		}

		@Override
		public String toString() {
			// String prefix = isFolder ? "/" : "";
			String appendix = isDirctory ? "/" : "." + extension;
			return fileName + appendix;
		}
	}

	public class FileSystem {
		File root = new File();
		File current = root;

		public FileSystem() {
			root.fileName = "~";
			root.isDirctory = true;
		}

		public boolean create(String name, boolean isFolder) {
			if (current.isDirctory) {
				File newFile = new File();
				if (isFolder) {
					newFile.fileName = name;
				} else {
					String[] names = name.split("\\.");
					newFile.fileName = names[0];
					newFile.extension = names[1];
				}
				newFile.isDirctory = isFolder;
				newFile.upperLevel = current;
				current.children.add(newFile);
				return true;
			} else {
				System.out.println("Can only create a file under a directory");
				return false;
			}
		}

		public void list() {
			for (File file : current.children) {
				System.out.printf("%5d %40s\n", file.size, file);
			}
		}

		public boolean changeDirectory(String dir) {
			if (dir.equals("..") && current.upperLevel != null) {
				current = current.upperLevel;
				return true;
			}
			for (File file : current.children) {
				if (file.getName().equals(dir)) {
					current = file;
				}
				return true;
			}
			return false;
		}

		public boolean remove(String[] names) {
			List<File> filesToRemove = new LinkedList<File>();
			boolean deleted = false;
			for (File file : current.children) {
				for (String name : names) {
					if (file.getName().equals(name))
						filesToRemove.add(file);
				}
			}
			for (File file : filesToRemove) {
				current.children.remove(file);
				deleted = true;
			}
			return deleted;
		}
	}

	public static String readCommand(File current) {

		System.out.println(current + "$ ");
		String input = new String();
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		try {
			input = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	public static void main(String[] args) {
		System.out.println("Ffile system starts.");
		InMemoryFileSystem instance = new InMemoryFileSystem();
		// FileSystem system = instance.system;
		// String input = readCommand(system.current);

		FileSystem system = new InMemoryFileSystem().new FileSystem();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String input = sc.next();
			while (!input.equals("exit")) {
				if (input.contains("touch")) {
					String[] commands = input.split(" ");
					system.create(commands[1], false);
				} else if (input.contains("mkdir")) {
					String[] commands = input.split(" ");
					system.create(commands[1], true);
				} else if (input.contains("ls")) {
					system.list();
				} else if (input.contains("cd")) {
					String[] commands = input.split(" ");
					system.changeDirectory(commands[1]);
				} else if (input.contains("rm")) {
					String[] commands = input.split(" ");
					String[] names = new String[commands.length - 1];
					for (int i = 0; i < names.length; ++i)
						names[i] = commands[i + 1];
					system.remove(names);
				}
				input = readCommand(system.current);
			}
		}

	}

}
