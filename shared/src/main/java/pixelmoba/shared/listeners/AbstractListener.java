package pixelmoba.shared.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import net.mostlyoriginal.api.system.core.PassiveSystem;
import pixelmoba.shared.dto.Dto;

public abstract class AbstractListener<T extends Dto> extends PassiveSystem implements Listener {

    private final Class<T> lClass;

    public AbstractListener(Class<T> lClass) {
        this.lClass = lClass;
    }

    @Override
    public void received(Connection connection, Object object) {
        if(lClass.isAssignableFrom(object.getClass())) trigger(connection, (T) object);
    }

    public abstract void trigger(Connection connection, T object);

}
