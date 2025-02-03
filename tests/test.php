<?php

function checkNumber($num) {
    try { // +1, 2
        if (!is_numeric($num)) { // +1, 3
            throw new Exception("Input is not a valid number.");
        }

        if ($num > 0) { // +1, 4
            echo "The number is positive.\n";
        } elseif ($num < 0) { // +1, 5
            echo "The number is negative.\n";
        } else {
            echo "The number is zero.\n";
        }

        switch ($num) {
            case 1: // +1, 6
                echo "The number is one.\n";
                break;
            case -1: // +1, 7
                echo "The number is negative one.\n";
                break;
            default:
                echo "The number is neither one nor negative one.\n";
                break;
        }
    } catch (Exception $e) {
        echo "Error: " . $e->getMessage() . "\n";
    }
}