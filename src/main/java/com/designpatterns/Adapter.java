package com.designpatterns;

/**
 * The Adapter pattern should be used when:
 * 
 * There is an existing class, and its interface does not match the one you need.
 * 
 * You want to create a reusable class that cooperates with unrelated or unforeseen classes, that is, classes that don’t
 * necessarily have compatible interfaces.
 * 
 * There are several existing subclasses to be use, but it’s impractical to adapt their interface by subclassing every
 * one. An object adapter can adapt the interface of its parent class.
 *
 */
public class Adapter {

	interface PaymentGatewayX {
		public String getCreditCardNum();

		public String getCustName();
	}

	interface PaymentGatewayY {
		public String getCustomerCardNum();

		public String getCustomerName();
	}

	class PaymentGatewayXToYAdapter implements PaymentGatewayY {

		private PaymentGatewayX gatewayX;

		public PaymentGatewayXToYAdapter(PaymentGatewayX gatewayX) {
			this.gatewayX = gatewayX;
		}

		@Override
		public String getCustomerCardNum() {
			return gatewayX.getCreditCardNum();
		}

		@Override
		public String getCustomerName() {
			return gatewayX.getCustName();
		}
	}
}
