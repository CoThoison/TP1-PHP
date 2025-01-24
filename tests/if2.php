<?php

function f2() {
    
    $a = '123';

    $i = 3;


    if (($i == 2) or ($i ==3)) {

        if ($i == 2) {
            $a = '456';
        } elseif ($i == 3) {
            $a = '789';
        } else {
            $a = '789';
        }
    }

    $df_y = $a;

    return;
}

f2();
