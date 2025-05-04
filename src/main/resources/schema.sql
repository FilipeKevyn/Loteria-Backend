CREATE TABLE tb_pool (
    id UUID PRIMARY KEY,
    title VARCHAR(100),
    type VARCHAR(50),
    value_total_invested DECIMAL(10, 2)
);

CREATE TABLE tb_bet (
    id UUID PRIMARY KEY,
    value_invested DECIMAL(10, 2),
    game_type VARCHAR(50),
    matched INT,
    pool_id UUID,
    FOREIGN KEY (pool_id) REFERENCES tb_pool(id)
);

CREATE TABLE tb_bet_number (
    id UUID PRIMARY KEY,
    number INT,
    matched BOOLEAN,
    pool_id UUID,
    FOREIGN KEY (pool_id) REFERENCES tb_pool(id)
);

CREATE TABLE tb_bet_numbers_bet (
    bet_id UUID,
    bet_number_id UUID,
    PRIMARY KEY (bet_id, bet_number_id),
    FOREIGN KEY (bet_id) REFERENCES tb_bet(id),
    FOREIGN KEY (bet_number_id) REFERENCES tb_bet_number(id)
);

CREATE TABLE tb_contest (
    id UUID PRIMARY KEY,
    draw_numbers TEXT,
    pool_id UUID UNIQUE,
    FOREIGN KEY (pool_id) REFERENCES tb_pool(id)
);