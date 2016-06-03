import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class McPatternsGUI extends JFrame {
	private McPatternsPresenter presenter;
	
	public McPatternsGUI(McPatternsPresenter presenter) {
		this.presenter = presenter;
		presenter.attachView(this);
		showGUI();
	}
	
	/**
	 * displays the gui and uses the presenter to update the gui
	 */
	private void showGUI() {
		presenter.loadMenuItems(); //loads the items on the menu
		final JButton[] itemButtons = new JButton[presenter.getButtonLabels().length]; //creates the buttons for menu items
		final JFrame theFrame = new JFrame("McPatterns");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setLayout(new BorderLayout());
		
		JPanel title = new JPanel(new FlowLayout());
		title.add(new JLabel("Welcome to McPatterns"));

		JPanel orderPane = new JPanel();
		orderPane.setLayout(new BoxLayout(orderPane, BoxLayout.PAGE_AXIS));
		final JLabel orderDetails = new JLabel("Your order");
		orderPane.setBorder(BorderFactory.createRaisedBevelBorder());
		orderPane.add(orderDetails);
		final JTextField ccEntry = new JTextField("Enter CC #");
		final JTextArea currentOrder = new JTextArea(); //displays the current order
		JScrollPane p = new JScrollPane(currentOrder);
		p.setPreferredSize(new Dimension(200, 400));
		p.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		currentOrder.setEditable(false);
		orderPane.add(p);
		final JTextField price = new JTextField("Total: $0.00"); //displays current order price
		price.setEditable(false);
		orderPane.add(price);
		
		final JButton confirm = new JButton("Place Order"); //create confirm button
		confirm.setEnabled(presenter.showCancelConfirm()); //disables the confirm button if there is no order
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ccEntry.getText().equals("Enter CC #")){  //did not enter a cc#
					orderDetails.setText("Did not enter CC #");
				}
				else if(ccEntry.getText().length() >12){ //cc# passes initial check
					CreditCard check = new CreditCard(ccEntry.getText()); //begin check to see if cc# is valid
					
					if(check.check() != null){
						CreditCard card = check.check();  //create cc type
						orderDetails.setText("Order confrimed for " + card.displayName()+": "+ ccEntry.getText());
						presenter.printOrder();  //prints order receipt
						confirm.setEnabled(false); //order is done to confirm is disabled until new order
						if(itemButtons[0].isEnabled()){ //disable menu buttons until cc# is validated or order canceled
							for(int i = 0; i<itemButtons.length; i++){
								itemButtons[i].setEnabled(false);
							}
						}
					}
					else{
					orderDetails.setText("Order declined for " + ccEntry.getText() + " Must enter another card number or cancel order");
					if(itemButtons[0].isEnabled()){ //disable menu buttons until cc# is validated or order canceled
						for(int i = 0; i<itemButtons.length; i++){
							itemButtons[i].setEnabled(false);
						}
					}
				}
				}
				else{
					orderDetails.setText("Order declined for " + ccEntry.getText() + " Must enter another card number or cancel order");
					if(itemButtons[0].isEnabled()){ //disable menu buttons until cc# is validated or order canceled
						for(int i = 0; i<itemButtons.length; i++){
							itemButtons[i].setEnabled(false);
						}
					}
				}
			}
		});
		
		final JButton cancel = new JButton("Cancel Order"); //create cancel button
		cancel.setEnabled(presenter.showCancelConfirm()); //disable button until there is an order
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderDetails.setText("Order cancelled");
				price.setText("Total: $0.00");
				currentOrder.setText("");
				presenter.clearOrder(); //clear order
				cancel.setEnabled(presenter.showCancelConfirm()); //disable cancel and confirm buttons
				confirm.setEnabled(presenter.showCancelConfirm());
				if(!itemButtons[0].isEnabled()){ //enable item buttons if order is cleared after a failed cc validation
					for(int i = 0; i<itemButtons.length; i++){
						itemButtons[i].setEnabled(true);
					}
				}
				ccEntry.setText("Enter CC #"); //set default text in cc field
			}
		});
		
		orderPane.add(ccEntry);
		orderPane.add(confirm);
		orderPane.add(cancel);
		
		JPanel buttonPanel = new JPanel(); //create panel for buttons
		buttonPanel.setLayout(new GridLayout(0,4)); //gridlayout with 4 columns
		String[] buttonLabels = presenter.getButtonLabels(); //ask presenter for menu items
		for(int i = 0; i<buttonLabels.length; i++){ //create menu buttons add actions
			itemButtons[i] = new JButton(buttonLabels[i]);
			itemButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(orderDetails.getText().equals("Order cancelled") || orderDetails.getText().equals("Did not enter CC #")) {
							orderDetails.setText("Your Order"); //sets prompt back to your order after cancel or Did not enter CC #
						}
					JButton current = (JButton)e.getSource();
					currentOrder.append(current.getText() + " $" + presenter.getTextPrice(current.getText()) + "\n"); //add to order display
					presenter.addToOrder(current.getText()); //add to order data structure
					price.setText(presenter.getCurrentTotal()); //display current price
					cancel.setEnabled(presenter.showCancelConfirm()); //enable confirm cancel buttons if disabled
					confirm.setEnabled(presenter.showCancelConfirm());
				}
			});
			buttonPanel.add(itemButtons[i], BorderLayout.WEST);
		}
		
		theFrame.add(title,BorderLayout.NORTH);
		theFrame.add(buttonPanel, BorderLayout.CENTER);
		theFrame.add(orderPane, BorderLayout.EAST);
		theFrame.setPreferredSize(new Dimension(1000, 600)); //ideal dimension for 4 column menu
		theFrame.pack();
		theFrame.setVisible(true);
	}
	
	public void printOrder(String s){
		System.out.print(s);
	}
}
