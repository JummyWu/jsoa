package com.github.jummy.base.id;

/**
 * @author: jummy
 * @create_date: 13/09/2021 2:53 下午
 * @desc:
 */
public class SnowflakeIdGenerate extends AbstractIdGenerate<Long> {
    public SnowflakeIdGenerate(final long machineCode) {
        super(machineCode);
    }

    @Override
    public Long generate() {
        return super.generateLong();
    }
}
