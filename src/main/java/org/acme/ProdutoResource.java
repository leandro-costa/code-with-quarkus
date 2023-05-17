package org.acme;

import java.util.List;
import java.util.NoSuchElementException;

import org.acme.model.Produto;
import org.acme.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/v1/produto")
public class ProdutoResource {

    @Autowired
    ProdutoRepository repositorio;

    @GET
    public List<Produto> listarTodos() {
        return repositorio.findAll();
    }

    
    @GET
    @Path("{id}")
    public Produto obter(@PathParam(value = "id") Long id) {
        return repositorio.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @POST
    public Produto novo(Produto novoProduto) {        
        return repositorio.save(novoProduto);
    }

    @PUT
    @Path("{id}")
    public Produto editar(@PathParam("id")  Long id, Produto novoProduto) {
        return repositorio.findById(id).map(
            produto -> {
                produto.setNome(novoProduto.getNome());
                produto.setDescricao(novoProduto.getDescricao());
                return repositorio.save(produto);
            }
        ).orElseGet(
            () -> {
                novoProduto.setId(id);
                return repositorio.save(novoProduto);
            }
        );
    }

    @DELETE
    @Path("{id}")
    public void excluir(@PathParam("id")  Long id) {
        repositorio.deleteById(id);
    }
}
