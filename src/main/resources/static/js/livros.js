const apiUrl = '/livros';

async function carregarLivros() {
  const res = await fetch(apiUrl);
  const livros = await res.json();

  const tabela = document.getElementById('tabelaLivros');
  tabela.innerHTML = '';

  livros.forEach(livro => {
    const linha = document.createElement('tr');
    linha.innerHTML = `
      <td>${livro.id}</td>
      <td>${livro.descricao}</td>
      <td>${livro.autor}</td>
      <td>${livro.ano}</td>
      <td>
        <button onclick="editarLivro(${livro.id})">Editar</button>
        <button onclick="excluirLivro(${livro.id})">Excluir</button>
      </td>
    `;
    tabela.appendChild(linha);
  });
}

async function excluirLivro(id) {
  if (!confirm('Confirma exclusão?')) return;
  await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
  carregarLivros();
}

function editarLivro(id) {
  // Redireciona para página de edição com query string
  window.location.href = `/livros/form.html?id=${id}`;
}

async function salvarLivro(event) {
  event.preventDefault();
  const id = document.getElementById('id').value;
  const descricao = document.getElementById('descricao').value;
  const autor = document.getElementById('autor').value;
  const ano = document.getElementById('ano').value;

  const metodo = id ? 'PUT' : 'POST';
  const url = id ? `${apiUrl}/${id}` : apiUrl;

  const livro = { descricao, autor, ano: parseInt(ano) };

  await fetch(url, {
    method: metodo,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(livro),
  });

  window.location.href = '/livros.html'; // volta para lista
}

// Se estiver na página de formulário, e tem id na query, carrega os dados para editar:
async function carregarLivroParaEdicao() {
  const params = new URLSearchParams(window.location.search);
  const id = params.get('id');
  if (!id) return;

  const res = await fetch(`${apiUrl}/${id}`);
  const livro = await res.json();

  document.getElementById('id').value = livro.id;
  document.getElementById('descricao').value = livro.descricao;
  document.getElementById('autor').value = livro.autor;
  document.getElementById('ano').value = livro.ano;
}

window.onload = () => {
  if (document.getElementById('tabelaLivros')) {
    carregarLivros();
  }

  if (document.getElementById('formLivro')) {
    carregarLivroParaEdicao();
    document.getElementById('formLivro').addEventListener('submit', salvarLivro);
  }
};
