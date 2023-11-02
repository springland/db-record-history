package com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple.BookEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple.BookRepository;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple.BookRevisionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/springdataenvers")
public class BookController {

    @Autowired
    BookRepository  bookRepository;



    @GetMapping("/web/springdataenvers/book")
    public String book(Model  model){


        List<BookRevisionDTO> bookRevisionDTOList = getAllBooks() ;

        bookRevisionDTOList.stream().forEach(System.out::println);
        model.addAttribute("books" , bookRevisionDTOList);


        return "book";
    }


    @GetMapping("/api/springdataenvers/book")
    @ResponseBody
    public List<BookRevisionDTO> getAllBooks() {
        List<BookEntity>  bookEntities = bookRepository.findAll();


        List<BookRevisionDTO> bookRevisionDTOList =
                bookEntities.stream().flatMap(book -> getBookHistory(book).stream()).collect(Collectors.toList());

        return bookRevisionDTOList ;
    }



    protected List<BookRevisionDTO> getBookHistory(BookEntity bookEntity){

        Revisions<Integer , BookEntity>  revisions = this.bookRepository.findRevisions(bookEntity.getId());

        List<BookRevisionDTO> bookRevisionList = revisions.stream()
                 .map( rev -> mapToBookRevision(rev))
                 .collect(Collectors.toList());
        return bookRevisionList ;
    }


    protected BookRevisionDTO mapToBookRevision(Revision<Integer , BookEntity> bookRevision){

        ModelMapper modelMapper = new ModelMapper();
        BookRevisionDTO bookRevisionDTO = modelMapper.map(bookRevision.getEntity() , BookRevisionDTO.class);
        bookRevisionDTO.setRevisionNumber(bookRevision.getRevisionNumber().orElse(null));
        return bookRevisionDTO ;
    }

}
