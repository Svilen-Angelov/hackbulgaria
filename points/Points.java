package points;

import java.util.Scanner;

public class Points {

	private static int x;
	private static int y;
	private static boolean isWarped = false;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Plese enter the directions string : ");
		String directions = sc.nextLine();

		System.out.println("Plese enter the starting coordinates for X and Y : ");
		System.out.print("X : ");
		x = sc.nextInt();
		System.out.print("Y : ");
		y = sc.nextInt();

		for (int i = 0; i < directions.length(); i++) {

			if (directions.charAt(i) == '~') {
				isWarped = !isWarped;
				continue;
			}

			if (isWarped) {
				Warped(directions.charAt(i));
			} else {
				notWarped(directions.charAt(i));
			}

		}

		System.out.println("( " + x + " , " + y + " )");
		sc.close();

	}

	public static void notWarped(char direction) {

		switch (direction) {
		case '>':
			x++;
			break;
		case '<':
			x--;
			break;
		case '^':
			y--;
			break;
		case 'v':
			y++;
			break;
		default:
			System.out.println("Invalid character in the string");
		}

	}

	public static void Warped(char direction) {

		switch (direction) {
		case '<':
			x++;
			break;
		case '>':
			x--;
			break;
		case 'v':
			y--;
			break;
		case '^':
			y++;
			break;
		default:
			System.out.println("Invalid character in the string");
		}

	}
}
