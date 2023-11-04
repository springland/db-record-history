package com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/springdataenvers")
public class BookController {

    @Autowired
    BookRepository  bookRepository;



    @GetMapping("/web/springdataenvers/books")
    public String book(Model  model){


        List<List<BookRevisionDTO>> listOfBookRevisionList = getAllBooks(false) ;

        model.addAttribute("books" , listOfBookRevisionList);


        return "book";
    }

    @GetMapping("/web/springdataenvers/booksincludedeleted")
    public String booksIncludeDeleted(Model  model){


        List<List<BookRevisionDTO>> listOfBookRevisionList = getAllBooks(true) ;

        model.addAttribute("books" , listOfBookRevisionList);


        return "book";
    }


    @ResponseBody
    public List<List<BookRevisionDTO>> getAllBooks(boolean includeDeleted) {
        List<BookEntity>  bookEntities ;
        if(includeDeleted){
            bookEntities = bookRepository.selectAllBooksNativeIncludeDeleted();

        }
        else {
            bookEntities= bookRepository.findAll();

        }


        List<List<BookRevisionDTO>> bookRevisionDTOList =
                bookEntities.stream().map(book -> getBookHistory(book)).collect(Collectors.toList());

        return bookRevisionDTOList ;
    }



    protected List<BookRevisionDTO> getBookHistory(BookEntity bookEntity){

        Revisions<Integer , BookEntity>  revisions = this.bookRepository.findRevisions(bookEntity.getId());

        List<BookRevisionDTO> bookRevisionList = revisions.stream()
                 .map( rev -> mapToBookRevision(bookEntity , rev))
                 .collect(Collectors.toList());
        Collections.reverse(bookRevisionList) ;
        return bookRevisionList ;
    }


    protected BookRevisionDTO mapToBookRevision(BookEntity bookEntity , Revision<Integer , BookEntity> bookRevision){

        ModelMapper modelMapper = new ModelMapper();
        BookRevisionDTO bookRevisionDTO ;


        if(bookRevision.getMetadata().getRevisionType() == RevisionMetadata.RevisionType.DELETE){
            bookRevisionDTO = modelMapper.map(bookEntity , BookRevisionDTO.class);

            bookRevisionDTO.setLastModifiedDt( bookRevision.getRevisionInstant().map( instant -> instant.atZone(ZoneId.systemDefault()).toLocalDateTime()).get());
        }
        else{
            bookRevisionDTO  = modelMapper.map(bookRevision.getEntity() , BookRevisionDTO.class);
        }

        bookRevisionDTO.setRevisionNumber(bookRevision.getRevisionNumber().orElse(null));
        bookRevisionDTO.setRevisionType(bookRevision.getMetadata().getRevisionType());
        return bookRevisionDTO ;
    }

}
