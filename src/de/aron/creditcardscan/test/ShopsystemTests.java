package de.aron.creditcardscan.test;

import io.card.payment.CardIOActivity;
import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;
import com.jayway.android.robotium.solo.Solo;
import de.ronneby.barcodescanner.BuyProductActivity;
import de.ronneby.shopsystem.MainActivity;


/**
 * @author Ronneby
 *
 */
public class ShopsystemTests extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;
	private static final String PACKET_DEFAULT = "de.aron.creditcardscan";

	
	public ShopsystemTests() {
		super("de.aron.shopsystem", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());	
	}
	
	public void testAAEveryActivityIsReachable(){
		solo.assertCurrentActivity("Right Activity", MainActivity.class);
		solo.getText("Welcome");
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.button_add_product));
		solo.goBack();
		solo.getText("Welcome");
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_show_product));
		solo.goBack();
		solo.getText("Welcome");
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_add_credicard));
		solo.goBack();
		solo.getText("Welcome");
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_show_creditcard));
		solo.goBack();
		solo.getText("Welcome");
		solo.finishOpenedActivities();
	}
	

	public void testAddProdukt(){
		solo.assertCurrentActivity("Main-Activity", MainActivity.class);
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.button_add_product));
		solo.enterText(1, "Handy");
		solo.enterText(0, "291.0");
		solo.clickOnButton("Scan Product Barcode");
		solo.goBack();
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.button_add_product));
		solo.enterText(1, "Hemd");
		solo.enterText(0, "94.99");
		solo.clickOnButton("Scan Product Barcode");
		solo.goBack();
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.button_add_product));
		solo.enterText(1, "Kopfhörer");
		solo.enterText(0, "97.98");
		solo.clickOnButton("Scan Product Barcode");
		solo.goBack();
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_show_product));
		solo.finishOpenedActivities();
	}
	
	public void testDeleteProduct(){
		solo.assertCurrentActivity("Main-Activity", MainActivity.class);
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_show_product));
		solo.clickLongInList(0);
		solo.finishOpenedActivities();
	}
	
	public void testAddCard(){
		solo.assertCurrentActivity("Main-Activity", MainActivity.class);
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_add_credicard));
		Solo tempSolo =  new Solo(getInstrumentation(), getActivity());	
		tempSolo.assertCurrentActivity("CardIO-Activity", CardIOActivity.class);
		tempSolo.clickOnButton(0);
		Solo soloDataEntry =  new Solo(getInstrumentation(), getActivity());
		soloDataEntry.enterText(0, "5124588710004718");
		soloDataEntry.enterText(1, "1118");
		soloDataEntry.clickOnButton("Fertig");
		solo.assertCurrentActivity("Main-Activity", CardIOActivity.class);
		solo.finishOpenedActivities();
	}
	

	public void testClearCards(){
		solo.assertCurrentActivity("Main-Activity", MainActivity.class);
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_show_creditcard));
		solo.clickOnButton("Clear all");
	}
	

	public void testDeleteCard(){
		solo.assertCurrentActivity("Main-Activity", MainActivity.class);
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_show_creditcard));
		solo.clickLongInList(0);
		solo.clickOnText("Löschen");
		solo.goBack();
		solo.finishOpenedActivities();
	}
	

	public void testBuyThings(){
		solo.assertCurrentActivity("Main-Activity", MainActivity.class);
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_add_credicard));
		Solo tempSolo =  new Solo(getInstrumentation(), getActivity());	
		tempSolo.assertCurrentActivity("CardIO-Activity", CardIOActivity.class);
		tempSolo.clickOnButton(0);
		Solo soloDataEntry =  new Solo(getInstrumentation(), getActivity());
		soloDataEntry.enterText(0, "5124588710004718");
		soloDataEntry.enterText(1, "1116");
		soloDataEntry.clickOnButton("Fertig");
		solo.goBackToActivity("MainActivity");
		
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.button_add_product));
		solo.enterText(1, "Uhr");
		solo.enterText(0, "129.0");
		solo.clickOnButton("Scan Product Barcode");
		solo.goBack();
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.button_add_product));
		solo.enterText(1, "Hose");
		solo.enterText(0, "49.99");
		solo.clickOnButton("Scan Product Barcode");
		solo.goBack();
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.button_add_product));
		solo.enterText(1, "Headset");
		solo.enterText(0, "79.89");
		solo.clickOnButton("Scan Product Barcode");
		solo.goBack();
		
		solo.goBackToActivity("MainActivity");
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_show_product));
		solo.clickOnButton("Buy Now");
		solo.assertCurrentActivity("BuyProduct-Activity", BuyProductActivity.class);
		solo.clickLongInList(0);
		solo.clickOnText(solo.getString(de.ronneby.creditcardscan.R.string.AD_choose_card_yes));
		solo.getText("Success");
		solo.finishOpenedActivities();
		solo.clickOnButton("Continue Shopping");
	}

	public void testUpdateCard(){
		solo.assertCurrentActivity("Main-Activity", MainActivity.class);
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_show_creditcard));
		solo.clickLongInList(0);
		solo.clickOnText("Aktualisieren");
		solo.clearEditText(0);
		solo.clearEditText(1);
		solo.enterText(1, "5124588710004545");
		solo.enterText(0, "11");
		solo.enterText(0, "/");
		solo.enterText(0, "2017");
		solo.clickOnButton("Save");
		solo.goBack();
		solo.finishOpenedActivities();
	}
	
	public void testPurchaseWithNoProducts(){
		solo.assertCurrentActivity("Main-Activity", MainActivity.class);
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_show_product));
		solo.clickOnButton("Buy Now");
		solo.getText("No products to buy");
		solo.finishOpenedActivities();
	}
	

	public void testAddDummyProduct(){
		solo.assertCurrentActivity("Main-Activity", MainActivity.class);
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.button_add_product));
		solo.enterText(1, " ");
		solo.enterText(0, " ");
		solo.clickOnButton("Scan Product Barcode");
		solo.goBack();
		solo.finishOpenedActivities();
	}
	

	public void testPackage(){
		String current_package = getActivity().getPackageName().toString();
		Assert.assertEquals(PACKET_DEFAULT, current_package);
	}
	

	public void testResources(){
		Assert.assertEquals((de.ronneby.creditcardscan.R.id.btn_add_product), 0x7f080016);
		Assert.assertEquals((de.ronneby.creditcardscan.R.id.btn_show_product), 0x7f080015);
		Assert.assertEquals((de.ronneby.creditcardscan.R.id.btn_add_credit_card), 0x7f080014);
		Assert.assertEquals((de.ronneby.creditcardscan.R.id.btn_show_credit_card), 0x7f080013);
	}

	public void testValidationOfCardNumber(){
		solo.assertCurrentActivity("Main-Activity", MainActivity.class);
		solo.clickOnButton(solo.getString(de.ronneby.creditcardscan.R.string.Button_show_creditcard));
		solo.clickLongInList(0);
		solo.clickOnText("Aktualisieren");
		solo.clearEditText(0);
		solo.clearEditText(1);
		solo.enterText(1, "6512751283728729"); 
		solo.enterText(0, "11");
		solo.enterText(0, "/");
		solo.enterText(0, "2017");
		solo.clickOnButton("Save");
	}
}
