package com.project.loteria.service;

import com.project.loteria.dtos.PoolDTO;
import com.project.loteria.entities.Bet;
import com.project.loteria.entities.Pool;
import com.project.loteria.exceptions.PoolNotFoundException;
import com.project.loteria.repositories.PoolRepository;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class PoolService {
    @Autowired
    private PoolRepository repository;
    @Autowired
    private MinioClient minioClient;

    public Pool createPool(PoolDTO poolDTO){
        Pool pool = new Pool(poolDTO);
        return repository.save(pool);
    }

    public void deletePool(UUID id){
        Pool pool = findById(id);
        repository.delete(pool);
    }

    public Pool update(Pool pool){
        return repository.save(pool);
    }

    public Pool findById(UUID id){
        return repository.findById(id).orElseThrow(() -> new PoolNotFoundException(id));
    }

    public List<Bet> getAllBets(Pool pool){
        return pool.getBets().stream().toList();
    }

    public List<Pool> findAll(){
        return repository.findAll();
    }

    public void addBetToPool(Pool pool, Bet bet){
        pool.getBets().add(bet);
        sumValueTotal(pool, bet);
        repository.save(pool);
    }

    public void sumValueTotal(Pool pool, Bet bet){
        double valueTotal = pool.getValueTotal() + bet.getValueInvested();
        pool.setValueTotal(valueTotal);
    }

    public void subtractValueTotal(Pool pool, Bet bet){
        double valueTotal = pool.getValueTotal() - bet.getValueInvested();
        pool.setValueTotal(valueTotal);
    }


    public Pool uploadProof(UUID poolId, MultipartFile file) throws Exception {
        Pool pool = repository.findById(poolId)
                .orElseThrow(() -> new RuntimeException("Bolão não encontrado"));

        String fileName = UUID.randomUUID().toString();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket("proof")
                        .object(fileName)
                        .stream(file.getInputStream(), file.getInputStream().available(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        pool.setImageReference(fileName);
        return repository.save(pool);
    }

    public InputStream getProof(String fileName) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket("proof")
                        .object(fileName)
                        .build()
        );
    }
}
