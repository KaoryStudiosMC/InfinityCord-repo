package net.md_5.bungee.protocol.packet;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil; // Waterfall
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PluginMessage extends DefinedPacket
{

    public static final Function<String, String> MODERNISE = new Function<String, String>()
    {
        @Override
        public String apply(String tag)
        {
            // Transform as per Bukkit
            if ( tag.equals( "BungeeCord" ) )
            {
                return "bungeecord:main";
            }
            if ( tag.equals( "bungeecord:main" ) )
            {
                return "BungeeCord";
            }

            // Code that gets to here is UNLIKELY to be viable on the Bukkit side of side things,
            // but we keep it anyway. It will eventually be enforced API side.
            if ( tag.indexOf( ':' ) != -1 )
            {
                return tag;
            }

            return "legacy:" + tag.toLowerCase( Locale.ROOT );
        }
    };

    public PluginMessage(String tag, ByteBuf data, boolean allowExtendedPacket) {
        this(tag, ByteBufUtil.getBytes(data), allowExtendedPacket);
    }

    private String tag;
    private byte[] data;

    public void setData(byte[] data) {
        this.data = Preconditions.checkNotNull(data, "Null data");
    }

    public void setData(ByteBuf buf) {
        Preconditions.checkNotNull(buf, "Null buffer");
        setData(ByteBufUtil.getBytes(buf));
    }

    /**
     * Allow this packet to be sent as an "extended" packet.
     */
    private boolean allowExtendedPacket = false;

    @Override
    public void read(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion)
    {
        tag = ( protocolVersion >= ProtocolConstants.MINECRAFT_1_13 ) ? MODERNISE.apply( readString( buf ) ) : readString( buf, 20 );
        int maxSize = direction == ProtocolConstants.Direction.TO_SERVER ? Short.MAX_VALUE : 0x100000;
        Preconditions.checkArgument( buf.readableBytes() < maxSize );
        data = new byte[ buf.readableBytes() ];
        buf.readBytes( data );
    }

    @Override
    public void write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion)
    {
        writeString( ( protocolVersion >= ProtocolConstants.MINECRAFT_1_13 ) ? MODERNISE.apply( tag ) : tag, buf );
        buf.writeBytes( data );
    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception
    {
        handler.handle( this );
    }

    public DataInput getStream()
    {
        return new DataInputStream( new ByteArrayInputStream( data ) );
    }
}
