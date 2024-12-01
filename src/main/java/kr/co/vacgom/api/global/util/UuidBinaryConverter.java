package kr.co.vacgom.api.global.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Optional;
import java.util.UUID;

@Converter
public class UuidBinaryConverter implements AttributeConverter<UUID, byte[]> {

    private static final int UUID_BYTE_LENGTH = 16;

    @Override
    public byte[] convertToDatabaseColumn(UUID attribute) {
        return Optional.ofNullable(attribute)
                .map(this::uuidToBytes)
                .orElse(null);
    }

    @Override
    public UUID convertToEntityAttribute(byte[] dbData) {
        return bytesToUuid(dbData);
    }

    private byte[] uuidToBytes(UUID uuid) {
        byte[] bytes = new byte[UUID_BYTE_LENGTH];
        ByteBuffer.wrap(bytes)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits());
        return bytes;
    }

    private UUID bytesToUuid(byte[] dbData) {
        try {
            ByteBuffer bb = ByteBuffer.wrap(dbData).order(ByteOrder.BIG_ENDIAN);
            return new UUID(bb.getLong(), bb.getLong());
        } catch (Exception e) {
            return null;
        }
    }
}



