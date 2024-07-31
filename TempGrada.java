package accu;

public class TempGrada {
	
	private String grad;
	private double temp;
	
	public TempGrada(String grad, double temp) {
		this.grad = grad;
		this.temp = temp;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		if(grad!=null && grad.length()>=2) this.grad = grad;
		else throw new RuntimeException("Greska: Neispravno ime grada!");
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		if(temp<-70 || temp>50) throw new RuntimeException("Greska: Neispravna temperatura!");
		this.temp = temp;
	}

	@Override
	public String toString() {
		return "Grad:" + grad + ", temp=" + temp;
	}	
}
