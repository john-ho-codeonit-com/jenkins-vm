#!/usr/bin/env bash

multipass stop jenkins-vm
multipass delete jenkins-vm
multipass purge
