package ex15_model2;

public class ActionForward {
	private boolean redirect;
	private String view;
	public ActionForward() {}
	public ActionForward(boolean redirect, String view) {
		this.redirect = redirect;
		this.view = view;
	}
	// setter, getter
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	
}
