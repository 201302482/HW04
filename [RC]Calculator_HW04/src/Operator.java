
public enum Operator{
	ADD('+'){
		@Override
		public double evaluate(double firstOperand,double secondOperand){ return firstOperand + secondOperand; }
	},
	SUB('-'){
		@Override
		public double evaluate(double firstOperand,double secondOperand){ return firstOperand - secondOperand; }
	},
	MUL('*'){
		@Override
		public double evaluate(double firstOperand,double secondOperand){ return firstOperand * secondOperand; }
	},
	DIV('/'){
		@Override
		public double evaluate(double firstOperand,double secondOperand){ return firstOperand / secondOperand; }
	};
	
	private final char symbol;

	Operator(char symbol){ this.symbol = symbol;}

	public abstract double evaluate(double firstOperand, double secondOperand);

	public static Operator findOperator(char symbol){
		for(Operator op : values()){
			if(op.symbol == symbol){
			
				return op;
			}
		}
		throw new RuntimeException("Invalid Operator");

	}
}