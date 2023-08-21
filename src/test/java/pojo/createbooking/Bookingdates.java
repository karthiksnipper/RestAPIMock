package pojo.createbooking;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.qdox.parser.ParseException;

public class Bookingdates {
    public Date checkin;
    public Date checkout;
    
    public Date getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) throws java.text.ParseException {
		 try {
	            this.checkin = new SimpleDateFormat("yyyy-MM-dd").parse(checkin);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	}
	public Date getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) throws java.text.ParseException {
		try {
            this.checkin = new SimpleDateFormat("yyyy-MM-dd").parse(checkout);
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	
//	public void setCheckout(String checkout) {
//		this.checkout = checkout;
//	}
	
	
	
    }


