# Offline Gamer Hub (Android)

Projeto de aplicativo Android **100% offline** com estética gamer/neon, foco em jogos retrô, personalização e utilidades. Tudo é salvo localmente no dispositivo, sem contas, sem servidores e sem dependências de internet.

## Objetivos principais
- Funcionar totalmente offline (sem login online, DRM, telemetria, anúncios ou servidores).
- Interface gamer/neon/futurista com animações suaves e alta personalização.
- Estrutura modular para permitir expansão de recursos ao longo do tempo.

## Restrições obrigatórias
- **Sem internet obrigatória**
- **Sem conta online**
- **Sem servidor externo**
- **Sem DRM/telemetria/anúncios**

## Sistema global de cores (obrigatório)
O app utiliza 4 cores principais, configuráveis pelo usuário:
- **Cor 1** → parte superior da tela
- **Cor 2** → parte central (estado A)
- **Cor 3** → parte central (estado B) + parte inferior
- **Cor 4** → elementos animados que caem do topo

Regras:
- **Cor 2** alterna automaticamente com **Cor 3**
- Troca suave (animação)

## Elementos animados
- Elementos visuais caem do topo (ex.: flocos, cubos, pontos)
- Tipo selecionável
- Cor baseada na **Cor 4**
- Pode ativar/desativar

## Estrutura de módulos
- **Menu principal**: cards grandes para acesso rápido
- **Perfil/Login (local)**: nickname único, foto, bio, cor personalizada, tags
- **Menu Gamer**: jogos, puzzle, atalhos, configurações
- **Calendário**: eventos offline (mensal/semanal)
- **MP3/MP4**: biblioteca local por pastas
- **Anotações**: notas offline por pastas
- **Personalizar App**: presets/temas, paleta de cores, elementos animados
- **IA Offline (limitada)**: sugestões locais sem internet

## Estrutura sugerida de dados (offline)
```
/users
/games
/puzzles
/media
/notes
/themes
/configs
```

---

> Este repositório começa com a documentação e estrutura de pastas para orientar a implementação offline.

## Observação
Este repositório **não é um tutorial de instalação** e **não exige Android Studio ou outras ferramentas específicas**. Ele serve apenas para documentar o projeto, suas funcionalidades e regras.
