package med.voll.api.security;

import med.voll.api.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoSevice implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AutenticacaoSevice(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByLogin(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        return usuario;
    }
}
