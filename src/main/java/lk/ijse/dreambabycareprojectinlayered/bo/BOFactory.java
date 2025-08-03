package lk.ijse.dreambabycareprojectinlayered.bo;

import lk.ijse.dreambabycareprojectinlayered.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER, DISCOUNT, EMPLOYEE, INVENTORY, MATERIAL, MATERIAL_USAGE, ORDER_ITEM,
        ORDERS, PAYMENT, PLACE_ORDERS, PLACE_PRODUCTION, PRODUCTION, PRODUCTION_TASK,
        SHIPMENT, SUPPLIER, SUPPLY, TASK, USER;
    }

    public SuperBO getBO(BOFactory.BOTypes boType) {
        switch (boType) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case DISCOUNT:
                return new DiscountBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case INVENTORY:
                return new InventoryBOImpl();
            case MATERIAL:
                return new MaterialBOImpl();
            case MATERIAL_USAGE:
                return new MaterialUsageBOImpl();
            case ORDER_ITEM:
                return new OrderItemBOImpl();
            case ORDERS:
                return new OrdersBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PLACE_ORDERS:
                return new PlaceOrderBOImpl();
            case PLACE_PRODUCTION:
                return new PlaceProductionBOImpl();
            case PRODUCTION:
                return new ProductionBOImpl();
            case PRODUCTION_TASK:
                return new ProductionTaskBOImpl();
            case SHIPMENT:
                return new ShipmentBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SUPPLY:
                return new SupplyBOImpl();
            case TASK:
                return new TaskBOImpl();
            case USER:
                return new UserBOImpl();

            default:
                return null;
        }
    }

}
