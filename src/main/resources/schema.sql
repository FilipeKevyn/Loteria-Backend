CREATE TABLE tb_pool (
    id UUID PRIMARY KEY,
    title VARCHAR(100),
    type VARCHAR(50),
    value_total_invested DECIMAL(10, 2)
);

CREATE TABLE tb_contest (
    id UUID PRIMARY KEY,
    pool_id UUID UNIQUE,
    FOREIGN KEY (pool_id) REFERENCES tb_pool(id) ON DELETE CASCADE
);

CREATE TABLE tb_number (
    id UUID PRIMARY KEY,
    number INT,
    matched BOOLEAN,
    contest_id UUID,
    pool_id UUID,
    FOREIGN KEY (contest_id) REFERENCES tb_contest(id) ON DELETE CASCADE,
    FOREIGN KEY (pool_id) REFERENCES tb_pool(id) ON DELETE CASCADE
);

CREATE TABLE tb_bet (
    id UUID PRIMARY KEY,
    value_invested DECIMAL(10, 2),
    game_type VARCHAR(50),
    matched INT,
    pool_id UUID,
    FOREIGN KEY (pool_id) REFERENCES tb_pool(id) ON DELETE CASCADE
);

CREATE TABLE tb_numbers_bet (
    bet_id UUID,
    number_id UUID,
    PRIMARY KEY (bet_id, number_id),
    FOREIGN KEY (bet_id) REFERENCES tb_bet(id) ON DELETE CASCADE,
    FOREIGN KEY (number_id) REFERENCES tb_number(id) ON DELETE CASCADE
);