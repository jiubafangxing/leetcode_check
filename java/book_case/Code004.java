package book_case;
/*
 * An animal shelter, which holds only dogs and cats, operates on a strictly
 * "first in, first out" basis. People must adopt either the "oldest"
 * (based on arrival time) of all animals at the shelter, or they can select
 * whether they would prefer a dog or a cat (and will receive the oldest animal
 * of that type). They cannot select which specific animal they would like.
 *
 * Create the data structures to maintain this system and implement operations
 * such as:
 * - enqueue: Adds an animal to the shelter.
 * - dequeueAny: Dequeues the oldest animal of any type.
 * - dequeueDog: Dequeues the oldest dog.
 * - dequeueCat: Dequeues the oldest cat.
 *
 * You may use the built-in Linked List data structure.
 *
 * The enqueue method has an 'animal' parameter, where:
 * - animal[0] represents the number of the animal.
 * - animal[1] represents the type of the animal, with 0 for cat and 1 for dog.
 *
 * The dequeue* methods return [animal number, animal type]. If there's no animal
 * that can be adopted, they return [-1, -1].
 */
import java.util.*;
public class Code004{

	public static class Pet{
		public int no;

		public int time;
	}

	public static class AnimalShelf {
		Queue<Pet> catQueue;
		Queue<Pet> dogQueue;
		int time;

		int[] DEFAULT_RESULT = new int[2];

		public AnimalShelf() {
			catQueue = new LinkedList<Pet>();
			dogQueue = new LinkedList<Pet>();
			time = 0;
			DEFAULT_RESULT[0] = -1;
			DEFAULT_RESULT[1] = -1;
		}

		public void enqueue(int[] animal) {
			if(null == animal && animal.length < 2){
				return;
			}
			Queue<Pet> operateQueue = catQueue;
			if (animal[1] == 1){
				operateQueue = dogQueue;
			}
			Pet pet = new Pet();
			pet.no = animal[0];
			pet.time  = time++;
			operateQueue.add(pet);

		}

		public int[] dequeueAny() {
			int[] DEFAULT_RESULT = new int[2];
			DEFAULT_RESULT[0] = -1;
			DEFAULT_RESULT[1] = -1;
			if(catQueue.isEmpty() && dogQueue.isEmpty()){
				return DEFAULT_RESULT;
			}
			int type =0;
			int no = 0;
			if(catQueue.isEmpty()){
				type =1;
				Pet pet = dogQueue.remove();
				no = pet.no;
			}
			else if(dogQueue.isEmpty()){
				type =0;
				Pet pet = catQueue.remove();
				no = pet.no;
			}else{
				Pet dog =dogQueue.peek();
				Pet cat =catQueue.peek();
				Queue<Pet> operateQueue = catQueue;
				if(dog.time < cat.time){
					operateQueue = dogQueue;
					no = dog.no;
					type =1;
				}else{
					no = cat.no;
					type =0;
				}
				operateQueue.remove();

			}
			int[] result = new int[2];
			result[0]=no;
			result[1]=type;
			return result;

		}

		public int[] dequeueDog() {
			if(dogQueue.isEmpty()){
				return DEFAULT_RESULT;
			}
			int type =1;
			Pet pet = dogQueue.remove();
			int no = pet.no;
			int[] result = new int[2];
			result[0]=no;
			result[1]=type;
			return result;

		}

		public int[] dequeueCat() {
			if(catQueue.isEmpty()){
				return DEFAULT_RESULT;
			}
			int type =0;
			Pet pet = catQueue.remove();
			int no = pet.no;
			int[] result = new int[2];
			result[0]=no;
			result[1]=type;
			return result;
		}
	}

	public static void main(String[] args){
		int[][] task = new int[][] {{0, 0}, {1, 1}, {2, 0}};
		AnimalShelf animalShelf = new AnimalShelf();
		for (int[] task1 : task) {
			animalShelf.enqueue(task1);
		}
		p(animalShelf.dequeueAny());
		p(animalShelf.dequeueAny());
		p(animalShelf.dequeueAny());
	}

	public static void p(int[] args) {
		System.out.println(args[0]+":"+args[1]);
	}

}
