@namespace "matrix"

function read(filename, matrix_var,   row,size) {
    while ((getline row < filename) > 0)
        matrix_var[++size] = row
    close(filename)
}

function row(matrix_var, row_num) {
    print matrix_var[row_num]
}

function column(matrix_var, column_num,   row,i) {
    for (i = NF = length(matrix_var); i > 0; --i) {
        split(matrix_var[i], row)
        $i = row[column_num]
    }
    return $0
}
