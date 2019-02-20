package network.adapters;

import com.google.gson.*;
import transaction.Payment;

import java.lang.reflect.Type;

public class PaymentAdapter implements JsonSerializer<Payment>, JsonDeserializer<Payment> {
    @Override
    public JsonElement serialize(Payment payment, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.add("amount", new JsonPrimitive(payment.getPayment()));
        obj.add("type", new JsonPrimitive(payment.getPaymentType()));
        if (payment.getPaymentType().equals("CREDIT")) {
            obj.add("cardNumber", new JsonPrimitive(payment.getCardNumber()));
        }
        return obj;
    }

    @Override
    public Payment deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = new JsonObject();
        String paymentType = obj.get("type").getAsString();
        double amount = obj.get("amount").getAsDouble();
        String cardNumber = obj.get("cardNumber").getAsString();
        return new Payment(paymentType, amount, cardNumber);
    }
}
