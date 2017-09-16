package com.stexni.platformer;

import jdk.nashorn.internal.ir.Block;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class StdOutRenderer {
    private static final Logger LOGGER = LoggerFactory.getLogger(StdOutRenderer.class);

    public StdOutRenderer() {
        if(!Ansi.isEnabled()) {
            AnsiConsole.systemInstall();
        }
    }

    public void render(World world) {
        Ansi.ansi().eraseScreen();

        for(int y = -1; y <= world.getHeight(); ++y) {
            Collection<BlockRenderInfo> blocks = new ArrayList<>(world.getWidth() + 2);
            for(int x = -1; x <= world.getWidth(); ++x) {
                BlockRenderInfo renderInfo = BlockRenderInfo.forBlockId(world.blockAt(x,  y));
                blocks.add(renderInfo);
            }

            LOGGER.info("{}", Ansi.ansi().render(renderLine(blocks)).reset());
        }
    }

    private String renderLine(Collection<BlockRenderInfo> blocks) {
        StringBuilder sb = new StringBuilder(blocks.size());

        Ansi.Color current = null;

        for (BlockRenderInfo block : blocks) {
            if(current == null && block.color != null) {
                sb.append("@|")
                    .append(block.color.toString().toLowerCase())
                    .append(" ");
                current = block.color;
            } else if(block.color != current && block.color != null) {
                sb.append("|@@|")
                    .append(block.color.toString().toLowerCase())
                    .append(" ");

                current = block.color;
            }

            sb.append(block.appearance);
        }

        sb.append("|@");

        return sb.append('\n').toString();
    }

    private enum BlockRenderInfo {
        UNKNOWN(-1, '?', Ansi.Color.RED),
        AIR(0, ' ', null),
        DIRT(1, '#', Ansi.Color.YELLOW),
        VOID(2, '-', Ansi.Color.MAGENTA),
        GRASS(3, '#', Ansi.Color.GREEN);

        public final int blockId;
        public final char appearance;
        public final Ansi.Color color;

        private static final Map<Integer, BlockRenderInfo> blockIdMap;

        static {
            Map<Integer, BlockRenderInfo> tmp = new HashMap<>();

            for (BlockRenderInfo renderInfo : BlockRenderInfo.values()) {
                tmp.put(renderInfo.blockId, renderInfo);
            }

            blockIdMap = Collections.unmodifiableMap(tmp);
        }

        BlockRenderInfo(int blockId, char appearance, Ansi.Color color) {
            this.blockId = blockId;
            this.appearance = appearance;
            this.color = color;
        }

        public static BlockRenderInfo forBlockId(int blockId) {
            BlockRenderInfo renderInfo = blockIdMap.get(blockId);

            if(renderInfo == null) {
                renderInfo = BlockRenderInfo.UNKNOWN;
            }

            return renderInfo;
        }
    }
}
