package lk.ijse.dreambabycareprojectinlayered.dao;

import lk.ijse.dreambabycareprojectinlayered.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER, DISCOUNT, EMPLOYEE, INVENTORY, MATERIAL, MATERIAL_USAGE, ORDER_ITEM,
        ORDERS, PAYMENT, PRODUCTION, PRODUCTION_TASK, QUERY, SHIPMENT, SUPPLIER, SUPPLY,
        TASK, USER;
    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch (daoType) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case DISCOUNT:
                return new DiscountDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            case MATERIAL:
                return new MaterialDAOImpl();
            case MATERIAL_USAGE:
                return new MaterialUsageDAOImpl();
            case ORDER_ITEM:
                return new OrderItemDAOImpl();
            case ORDERS:
                return new OrdersDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PRODUCTION:
                return new ProductionDAOImpl();
            case PRODUCTION_TASK:
                return new ProductionTaskDAOImpl();
            case QUERY:
                return new QueryDaoImpl();
            case SHIPMENT:
                return new ShipmentDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLY:
                return new SupplyDAOImpl();
            case TASK:
                return new TaskDAOImpl();
            case USER:
                return new UserDAOImpl();

            default:
                return null;
        }
    }
}
