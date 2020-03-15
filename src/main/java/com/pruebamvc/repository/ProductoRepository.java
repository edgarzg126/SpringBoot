package com.pruebamvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebamvc.entity.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
