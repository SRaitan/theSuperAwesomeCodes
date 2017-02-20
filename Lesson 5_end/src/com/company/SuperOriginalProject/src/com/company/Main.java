package com.company;
import com.company.Animals.*;
import com.company.Shapeush.*;
import java.lang.*;

public class Main {
	public static void main(String[] args) {
		//region inheritance
		Animal[] zoo = {new Giraffe(), new Dog(), new Beagle()};
		for (Animal a : zoo)
			a.makesound();
		Giraffe g;
		Beagle b;
		Dog d = new Beagle();
		d.goToSleep();
		Animal a = new Beagle();
		//endregion

		//region inheritance, funcs &nullpointers
		Point3D p = new Point3D(2, 5, 4);
		System.out.println(p);
		System.out.println(largestDigit(1234567));
		//endregion

		//region casting
		Dog elDogin = new Beagle();
		Beagle shiloh = new Beagle();
		handleDog(shiloh);
		Beagle bb = (Beagle) elDogin;
		//beagle "is a" dog
		//endregion

		//region hashcode, final, static
		Point p9 = new Point(6, 3);
		Point p4 = new Point(2, 3);
		//Circle c = new Circle();
		//c.refresh();

		Giraffe nana=new Giraffe();
		Giraffe n;
		System.out.println(Giraffe.counter);
		Shape [] arr=new Shape[]{new Circle(), new Circle(), new Circle()};
		System.out.println(Shape.counter);
		//endregion
	}

	static void handleDog(Dog d) {
		if (d != null)
			d.bark();
	}

	static boolean divisbleBy7(int num) {
		if (num < 0)
			return divisbleBy7(-num);
		if (num == 0 || num == 7) return true;
		if (num < 10) return false;
		return divisbleBy7(num / 10 - 2 * (num - num / 10 * 10));
	}

	static int sumOfDigits(int num) {
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}

	static int finalSumOfDigits(int num) {
		int n = sumOfDigits(num);
		while (n > 9)
			n = sumOfDigits(n);
		return n;
	}

	static int largestDigit(int num) {
		int res = 0;
		while (num > 0) {
			if (num % 10 > res)
				res = num % 10;
			num /= 10;
		}
		return res;
	}

	static int reverseDigits(int num) {
		int rev = 0;
		int mone = 1;
		while (num > 0) {
			rev += (num % 10) * (mone);
			num /= 10;
			mone *= 10;
		}
		return rev;
	}

	static boolean divby3(int num) {
		if (num < 0)
			return divby3(-num);
		if (num == 0 || num == 3 || num == 6 || num == 9) return true;
		if (num < 10) return false;
		return divby3(finalSumOfDigits(num));
	}
}

	/*static void getAllFields(Object someClass) {
		System.out.println("GET ALL FIELDS");
		List<Field> result=new ArrayList<>();
		for (Class<?> c = someClass.getClass(); c != null; c = c.getSuperclass()) {
			Field[] fields = c.getDeclaredFields();
			for (Field classField : fields) {
				result.add(classField);
				System.out.println(classField.toString());
			}
		}
	}*/
	/*static boolean hadasaEquals(Object obj, Object obj2) {
		try {
			//if(obj.get)
			System.out.println("Hadasa equals");
			if (obj.getClass() != obj2.getClass())
				throw new InvalidParameterException();
			Object instance = new Object();
			Object instance2 = new Object();
			Field[] firstParam = obj.getClass().getDeclaredFields();
			Field[] secondParam = obj2.getClass().getDeclaredFields();
			for (int i = 0; i < firstParam.length; i++) {
				System.out.println(firstParam[i].get(instance));
				System.out.println(secondParam[i].get(instance2));
*//*			if (!firstParam.equals(secondParam))
				return false;
			return true;*//*
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}*/


