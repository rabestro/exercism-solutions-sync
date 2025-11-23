def word_key: ascii_downcase | explode | sort;

(.subject | length) as $size |
(.subject | ascii_downcase) as $subject |
(.subject | word_key) as $subject_key |

def is_candidate: length == $size and ascii_downcase != $subject;
def is_anagram: word_key == $subject_key;

[
    .candidates[] | 
    select(is_candidate) |
    select(is_anagram)
]
