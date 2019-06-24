package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Supplier;

import java.util.List;

public interface SupplierRepository {
    
    boolean addSupplier(Supplier supplier);
    Supplier getSupplier (int id);
    List<Supplier> getAllSuppliers();
    boolean updateSupplier(Supplier supplier);
    boolean deleteSupplier(Supplier supplier);
}
