package www.niuke.class_03;
//初级3
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code_03_StackAndQueueConvert {

	public static class TwoStacksQueue {
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;

		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();//data队列
			stackPop = new Stack<Integer>();//help队列
		}

		public void push(int pushInt) {
			stackPush.push(pushInt);
		}

		public int poll() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
				/**
				 *else if 部分可以换位dao()函数
				 * 此行为还可以换 peek 和 push 中
				 */
			}
			return stackPop.pop();
		}

		public int peek() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			return stackPop.peek();
		}
		//只有同时满足这两个行为才能倒，不管发生在什么时刻都是对的
		public void dao() {
			//Pop栈不是空没法倒
			if (!(stackPop.isEmpty())) {
				return;
			}
			//Push栈不空就要一下倒完
			while(!stackPush.isEmpty()) {
				stackPop.push(stackPush.pop());
			}
		}
	}

	public static class TwoQueuesStack {
		public Queue<Integer> queue;
		public Queue<Integer> help;

		public TwoQueuesStack() {
			queue = new LinkedList<Integer>();
			help = new LinkedList<Integer>();
		}

		public void push(int pushInt) {
			queue.add(pushInt);
		}

		public int peek() {
			if (queue.isEmpty()) {
				throw new RuntimeException("Stack is empty!");
			}
			while (queue.size() != 1) {
				help.add(queue.poll());
			}
			int res = queue.poll();
			help.add(res);
			swap();
			return res;
		}

		public int pop() {
			if (queue.isEmpty()) {
				throw new RuntimeException("Stack is empty!");
			}
			while (queue.size() > 1) {
				help.add(queue.poll());
			}
			int res = queue.poll();
			swap();
			return res;
		}
		//交换引用
		public void swap() {
			Queue<Integer> tmp = help;
			help = queue;
			queue = tmp;
		}

	}

}
