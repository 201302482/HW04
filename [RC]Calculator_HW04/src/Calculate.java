import java.util.Stack;

public class Calculate {
    private Stack<Character> _oStack;
    private Stack<Double> _vStack;
    private char[] _infix;
    private char[] _postfix;
    
    public Calculate()
    {
    	this._oStack = null;
    	this._postfix = null;
        this._infix = null;
        this._vStack = null;
    }
    
    public void setInfix(String anInfix)
    {
    	this._infix = anInfix.toCharArray(); // String 문자열을 char로 변환후 배열로 반환해주는 메소드
    }
    
    public String infix()
    {
    	if (this._infix != null)
    	    return String.valueOf(this._infix);
    	else return null;
    }
    
    public String postfix()
    {
    	if(this._postfix != null)
    	    return String.valueOf(this._postfix);
    	else return null;
    }
    
    public boolean infixToPostfix()
    {
    	int i;
    	int p;
    	char curToken, poppedToken, topToken;
    	this._oStack = new Stack<Character>();
    	this._postfix = new char[this._infix.length+1];
    	i = 0;
    	p = 0;
    	
    	while (i<this._infix.length)
    	{
    		curToken =_infix[i++];
    		if (Character.isLetter(curToken)){
    			System.out.print("\n문자를 입력하셨습니다. 옳바른 값을 입력해주세요.");
    			System.exit(0);
    		}
    		else if(Character.isDigit(curToken)) {
    			_postfix[p++] = curToken;
    		}
    		else{
    			if( curToken == ')' ){
    				if (! _oStack.isEmpty() ){
    					poppedToken = (char)_oStack.pop();
    				}
    				else 
    					return false;
    				
    				while(poppedToken != '(' ){
    					_postfix[p++] = poppedToken;
    					if (! _oStack.isEmpty() ){
    						poppedToken = (char) _oStack.pop();
    					}
    					else 
    						return false;
    				}
    			}
    			else {
    				int inComingP = inComingPrecedence(curToken);
    				if (! _oStack.isEmpty() ){
    					topToken = (char) _oStack.peek();
    					while(inStackPrecedence(topToken) >= inComingP) {
    						poppedToken = (char) _oStack.pop();
    						_postfix[p++] = poppedToken;
    						if( !_oStack.isEmpty() )
    							topToken = (char)_oStack.peek();
    						else
    							break;
    					}
    				}
    				this._oStack.push(curToken);
    				
    			}
    		}
    	}
    	if ( !this._oStack.isEmpty()){
    	this._postfix[p++] = this._oStack.pop();
    	}
        return true;
    	}
    
    private int inComingPrecedence(char aToken) {
    	if(aToken == '+')
    		return 12;
    	else if (aToken == '-')
    		return 12;
    	else if (aToken == '(')
    		return 20;
    	else if (aToken == ')')
    		return 19;
    	else if (aToken == '*')
    		return 13;
    	else if (aToken == '/')
    		return 13;
    	else if(aToken == '%')
    		return 13;
    	else if(aToken == '^')
    		return 17;
    	else if(aToken == '$')
    		return 0;
    	else 
    		return -1;
    }
    
    private int inStackPrecedence(char aToken) 
    {
    	if(aToken == '+')
    		return 12;
    	else if (aToken == '-')
    		return 12;
    	else if (aToken == '(')
    		return 0;
    	else if (aToken == ')')
    		return 19;
    	else if (aToken == '*')
    		return 13;
    	else if (aToken == '/')
    		return 13;
    	else if(aToken == '%')
    		return 13;
    	else if(aToken == '^')
    		return 16;
    	else if(aToken == '$')
    		return 0;
    	else 
    		return -1;
    }
    
    public double evalPostfix()
    {
    	int p;
    	char curToken;
    	this._vStack = new Stack<Double>();

    	
    	p = 0;
    	
    	while(p<this._postfix.length)
    	{
    		curToken = _postfix[p++];
    		if (Character.isDigit(curToken)){
    			this._vStack.push(Double.parseDouble(String.valueOf(curToken)));
  
    		}
    		else {
    			if(this._vStack.size() >= 2){
    			double cur = this._vStack.pop();
    			double pre = this._vStack.pop();
    			double result = 0;
    			
    			final Operator operator = Operator.findOperator(curToken);
    			result = operator.evaluate(pre, cur);
    			this._vStack.push(result);
    			}
    			else
    				break;
    			
    		}
    	}
    	double result2 = this._vStack.pop();
    	return result2;
    	
    	
    }


}
