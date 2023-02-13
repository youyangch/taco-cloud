package club.belence.tacocloud.dao;

import club.belence.tacocloud.entity.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
