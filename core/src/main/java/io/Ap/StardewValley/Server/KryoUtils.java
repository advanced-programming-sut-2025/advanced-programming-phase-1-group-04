package io.Ap.StardewValley.Server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import io.Ap.StardewValley.Model.Player.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class KryoUtils {
    private static final Kryo kryo = createKryo();

    private static Kryo createKryo() {
        Kryo kryo = new Kryo();

        CompatibleFieldSerializer<Player> playerSerializer = new CompatibleFieldSerializer<>(kryo, Player.class);
        //CompatibleFieldSerializer<String> StringSerializer = new CompatibleFieldSerializer<>(kryo, String.class);
        kryo.register(Player.class, playerSerializer);
        //kryo.register(String.class, StringSerializer);

        kryo.register(PlayerPosition.class);
        kryo.register(ChatMessage.class);

        return kryo;
    }

    public static Kryo getKryo() {
        return kryo;
    }

    public static <T> byte[] serialize(T obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Output output = new Output(baos)) {
            kryo.writeObject(output, obj);
            output.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(byte[] bytes, Class<T> type) {
        try (Input input = new Input(new ByteArrayInputStream(bytes))) {
            return kryo.readObject(input, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
