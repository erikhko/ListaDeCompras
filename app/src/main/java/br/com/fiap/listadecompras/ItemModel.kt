package br.com.fiap.listadecompras
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A anotação @Entity indica que esta classe representa uma tabela no banco de dados.
 *
 * Pelo nome da tabela não estar especificado pelo tableName, o nome da tabela será o mesmo da classe, por padrão
 */
@Entity
data class ItemModel(

    /**
     * autoGenerate = true -> significa que o Room vai gerar automaticamente um valor único para o id a cada nova inserção de item no banco de dados.
     */
    @PrimaryKey(autoGenerate = true)

    /**
     * O campo id é anotado com @PrimaryKey, o que indica que ele é a chave primária da tabela.
     */
    val id: Int = 0,

    /**
     * O campo name representa o nome do item na lista de compras.
     *
     * Esse campo será uma coluna na tabela ItemModel, onde serão armazenados os nomes dos itens.
     */
    val name: String
)