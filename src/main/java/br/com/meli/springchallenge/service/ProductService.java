package br.com.meli.springchallenge.service;

import br.com.meli.springchallenge.DTO.TicketDTO;
import br.com.meli.springchallenge.entity.ArticlesPurchaseEntity;
import br.com.meli.springchallenge.entity.ProductEntity;
import br.com.meli.springchallenge.entity.ShoppingCartEntity;
import br.com.meli.springchallenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductEntity> applyFilters(ProductEntity productEntity) throws IOException {

        List<ProductEntity> listAll = productRepository.listAll();
        List<ProductEntity> listCategoryFiltered = new ArrayList<>();
        List<ProductEntity> listFreeShippingFiltered = new ArrayList<>();
        List<ProductEntity> listBrandFiltered = new ArrayList<>();
        List<ProductEntity> listPriceFiltered = new ArrayList<>();
        List<ProductEntity> listPrestigeFiltered = new ArrayList<>();

        if(productEntity.getCategory() != null)
            listCategoryFiltered = listAll.stream().filter(product -> product.getCategory().equalsIgnoreCase(productEntity.getCategory())).collect(Collectors.toList());
        if(productEntity.getFreeShipping() != null)
            if(!listCategoryFiltered.isEmpty())
                listFreeShippingFiltered = listCategoryFiltered.stream().filter(product -> product.getFreeShipping().equals(productEntity.getFreeShipping())).collect(Collectors.toList());
            else
                listFreeShippingFiltered = listAll.stream().filter(product -> product.getFreeShipping().equals(productEntity.getFreeShipping())).collect(Collectors.toList());
        if(productEntity.getPrestige() != null)
            if(!listFreeShippingFiltered.isEmpty())
                listPrestigeFiltered = listFreeShippingFiltered.stream().filter(product -> product.getPrestige().equals(productEntity.getPrestige())).collect(Collectors.toList());
            else if(!listCategoryFiltered.isEmpty())
                listPrestigeFiltered = listCategoryFiltered.stream().filter(product -> product.getPrestige().equals(productEntity.getPrestige())).collect(Collectors.toList());
            else
                listPrestigeFiltered = listAll.stream().filter(product -> product.getPrestige().equals(productEntity.getPrestige())).collect(Collectors.toList());
        if(productEntity.getBrand() != null)
            if(!listPrestigeFiltered.isEmpty())
                listBrandFiltered = listPrestigeFiltered.stream().filter(product -> product.getBrand().equalsIgnoreCase(productEntity.getBrand())).collect(Collectors.toList());
            else if(!listFreeShippingFiltered.isEmpty())
                listBrandFiltered = listFreeShippingFiltered.stream().filter(product -> product.getBrand().equalsIgnoreCase(productEntity.getBrand())).collect(Collectors.toList());
            else if(!listCategoryFiltered.isEmpty())
                listBrandFiltered = listCategoryFiltered.stream().filter(product -> product.getBrand().equalsIgnoreCase(productEntity.getBrand())).collect(Collectors.toList());
            else
                listBrandFiltered = listAll.stream().filter(product -> product.getBrand().equalsIgnoreCase(productEntity.getBrand())).collect(Collectors.toList());
       if(productEntity.getPrice() != null)
           if(!listBrandFiltered.isEmpty())
               listPriceFiltered = listBrandFiltered.stream().filter(product -> product.getPrice().compareTo(productEntity.getPrice()) <= 0).collect(Collectors.toList());
           else if(!listPrestigeFiltered.isEmpty())
               listPriceFiltered = listPrestigeFiltered.stream().filter(product -> product.getPrice().compareTo(productEntity.getPrice()) <= 0).collect(Collectors.toList());
           else if(!listFreeShippingFiltered.isEmpty())
               listPriceFiltered = listFreeShippingFiltered.stream().filter(product -> product.getPrice().compareTo(productEntity.getPrice()) <= 0).collect(Collectors.toList());
           else if(!listCategoryFiltered.isEmpty())
               listPriceFiltered = listCategoryFiltered.stream().filter(product -> product.getPrice().compareTo(productEntity.getPrice()) <= 0).collect(Collectors.toList());
           else
               listPriceFiltered = listAll.stream().filter(product -> product.getPrice().compareTo(productEntity.getPrice()) <= 0).collect(Collectors.toList());


        return !listPriceFiltered.isEmpty() ? listPriceFiltered
                : !listBrandFiltered.isEmpty() ? listBrandFiltered
                : !listPrestigeFiltered.isEmpty() ? listPrestigeFiltered
                : !listFreeShippingFiltered.isEmpty() ? listFreeShippingFiltered
                : !listCategoryFiltered.isEmpty() ? listCategoryFiltered
                : listAll;
    }

    public ProductEntity registerProduct(ProductEntity productEntity) throws IOException {
       productRepository.save(productEntity);
       return productEntity;
    }

    public List<ProductEntity> listAll() throws IOException {
        return productRepository.listAll();
    }

    public TicketDTO buyProduct(ShoppingCartEntity shoppingCart) throws Exception {

        List<ProductEntity> productEntities = new ArrayList<>();
        TicketDTO ticketDTO;
        BigDecimal total = new BigDecimal(0);

        for(ArticlesPurchaseEntity articlesPurchaseEntity : shoppingCart.getArticlesPurchaseRequest()){

            ProductEntity productEntity = productRepository.findOneById(articlesPurchaseEntity.getProductId());

            if(productEntity.getQuantity() >= articlesPurchaseEntity.getQuantity()){

                total.add(productEntity.getPrice()) ;

                productEntities.add(
                              ProductEntity.builder()
                             .productId(productEntity.getProductId())
                             .name(productEntity.getName())
                             .category(productEntity.getCategory())
                             .brand(productEntity.getBrand())
                             .price(productEntity.getPrice())
                             .quantity(articlesPurchaseEntity.getQuantity())
                             .freeShipping(productEntity.getFreeShipping())
                             .prestige(productEntity.getPrestige())
                             .build()
                );
            }else{
                throw  new Exception("Quantidade do item " + productEntity.getName() + " Nao disponivel");
            }
        }

        return TicketDTO.builder().articles(productEntities).total(total).id(10L).build();

    }
}
