package com.example.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.models.Categoria;
import com.example.demo.services.CategoriaService;
import com.example.demo.models.Evento;
import com.example.demo.models.EventoDTO;
import com.example.demo.models.Utente;
import com.example.demo.services.EventoService;
import com.example.demo.services.UtenteService;

@RestController
@RequestMapping("/api/eventi")
@CrossOrigin(origins = "http://localhost:4200")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CategoriaService categoriaService;


    //HTTP HANDLERS
    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventi() {
        List<Evento> eventi = eventoService.getAllEventi();
        return new ResponseEntity<>(eventi, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventoById(@PathVariable("id") Long eventoId){
        Evento evento = eventoService.getEventoById(eventoId);
	    
        return new ResponseEntity<>(evento, HttpStatus.OK); 
    }

    @GetMapping("/organizzatore/{id}")
    public ResponseEntity<?> getEventoByOrganizzatore(@PathVariable("id") Long organizzatoreId){
        List<Evento> eventi = eventoService.getEventoByOrganizzatore(organizzatoreId);
        
        return new ResponseEntity<>(eventi, HttpStatus.OK); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody EventoDTO dto) {
        
        Evento evento = eventoService.getEventoById(id);
        if (evento == null) {
            return ResponseEntity.notFound().build();
        }

        System.out.println("DTO RICEVUTO: " + dto.toString());

        Categoria c = categoriaService.getCategoriaById(dto.getIdCategoria());

        evento.setTitolo(dto.getTitolo());
        evento.setDescrizione(dto.getDescrizione());
        evento.setLuogo(dto.getLuogo());
        evento.setDataora(dto.getDataora());
        evento.setCosto(dto.getCosto());
        evento.setCategoria(c);
        evento.setImage_url(dto.getImage_url());

        eventoService.salva(evento);
        return ResponseEntity.ok(evento);
    }

    @PostMapping("/{organizzatoreId}")
    public ResponseEntity<?> creaEvento(@PathVariable Long organizzatoreId, @RequestBody EventoDTO dto) {
        Evento evento = new Evento();
	    System.out.println("================\nEVENTO RICEVUTO:\n" + dto + "\n====================\n");
        evento.setTitolo(dto.getTitolo());
        evento.setDescrizione(dto.getDescrizione());
        evento.setLuogo(dto.getLuogo());
        evento.setDataora(dto.getDataora());
        evento.setCosto(dto.getCosto());
        evento.setN_posti(dto.getN_posti());
        evento.setImage_url(dto.getImage_url());
        // recupera entità da ID

        //organizzatore
        Utente organizzatore = utenteService.getUtenteById(organizzatoreId);
        
        //categoria
        Categoria categoria = categoriaService.getCategoriaById(dto.getIdCategoria());

        evento.setOrganizzatore(organizzatore);
        evento.setCategoria(categoria);

        eventoService.salva(evento);

        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }

    @PostMapping("/upload-img")
    public ResponseEntity<?> uploadImmagine(@RequestParam("file") MultipartFile img){
        try{
            String uploadDir = "uploads/";
            
            String originalFilename = img.getOriginalFilename();

            System.out.println(originalFilename);

            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }else{ throw new Exception("estensione immagine mancante");}

            String fileName = "img-evento" + System.currentTimeMillis() + extension;

            Path path = Paths.get(uploadDir + fileName);

            Files.write(path,img.getBytes());
            
            String immagineUrl = fileName;

            return ResponseEntity.ok(immagineUrl);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore upload");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
        Path file = Paths.get("/home/giulio/Desktop/Personale/terzo_anno/software-eng/vivi_la_provincia/backend/uploads").resolve(filename);

        System.out.println("RICHIESTA RISORSA A:" + file.toString());

        if (!Files.exists(file)) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new UrlResource(file.toUri());
        
        String contentType = Files.probeContentType(file);
        if (contentType == null ) contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);

    }

}
