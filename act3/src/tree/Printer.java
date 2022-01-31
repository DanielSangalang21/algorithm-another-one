package tree;

public interface Printer<T> {
	public <T extends String> void print(T data);
	public static void yess() {
		System.out.println("Hey");
		//Comparable c;
	}
}
