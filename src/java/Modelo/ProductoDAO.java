/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author NN
 */

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;



    // Operacion CRUD

    public List Listar() {
        String sql = "select * from producto";
        List<Producto> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Producto em = new Producto();
                em.setIdProducto(rs.getInt(1));
                em.setNombres(rs.getString(2));
                em.setPrecio(rs.getDouble(3));
                em.setStock(rs.getInt(4));
                em.setEstado(rs.getString(5));
                lista.add(em);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public int agregar(Producto pro) {
        String sql = "insert into Producto(Nombres, Precio, Stock, Estado) values(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombres());
            ps.setDouble(2,  pro.getPrecio());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public Producto listarId(int id) {
        Producto pro = new Producto();
        String sql = "select * from producto where IdProducto="+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                pro.setIdProducto(rs.getInt(1));
                pro.setNombres(rs.getString(2));
                pro.setPrecio(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));
            }
        } catch (Exception e) {

        }
        return pro;
    }

    public int actualizar(Producto pro) {
        String sql = "update producto set  Nombres=?, Precio=?, Stock=?, Estado=? where IdProducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombres());
            ps.setDouble(2, pro.getPrecio());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.setInt(6, pro.getIdProducto());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;

    }

    public void delete(int id) {
        String sql = "delete from producto where IdProducto=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
